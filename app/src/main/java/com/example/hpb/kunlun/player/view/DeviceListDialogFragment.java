/*
 * Copyright (C) 2014 Kevin Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hpb.kunlun.player.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hpb.kunlun.Henson;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.dlna.upnp.SystemManager;

import org.fourthline.cling.model.meta.Device;

import java.util.Collection;

public class DeviceListDialogFragment extends DialogFragment {
    private static final String TAG = DeviceListDialogFragment.class.getSimpleName();
    private ArrayAdapter<Device> mArrayAdapter;
    private AsyncTask mUpdateDeviceTask;

    public static DeviceListDialogFragment newInstance() {
        DeviceListDialogFragment fragment = new DeviceListDialogFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_device, container, false);
        getDialog().setTitle("Select Active Device");

        ListView listView = (ListView) view.findViewById(android.R.id.list);
        mArrayAdapter = new DeviceListAdapter(getActivity(), 0);
        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(selectDeviceListener);

        return view;
    }

    private AdapterView.OnItemClickListener selectDeviceListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Device device = mArrayAdapter.getItem(position);
            SystemManager.getInstance().setSelectedDevice(device);
            Intent upnpIntent = Henson.with(getActivity()).gotoUpnpActivity().title(device.getDetails().getFriendlyName())
                    .build();
            startActivity(upnpIntent);
            dismiss();
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        mUpdateDeviceTask = new UpdateDeviceListTask().execute();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mUpdateDeviceTask != null) {
            mUpdateDeviceTask.cancel(true);
            mUpdateDeviceTask = null;
        }
    }

    @Override
    public void onDestroy() {
        if (mUpdateDeviceTask != null)
            mUpdateDeviceTask.cancel(true);

        super.onDestroy();
    }

    private class DeviceListAdapter extends ArrayAdapter<Device> {
        private LayoutInflater mInflater;

        public DeviceListAdapter(Context context, int resource) {
            super(context, resource);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = mInflater.inflate(R.layout.item_device, null);

            Device item = getItem(position);
            if (item == null) {
                return convertView;
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.listview_item_image);
            imageView.setBackgroundResource(R.drawable.ic_action_dock);

            TextView textView = (TextView) convertView.findViewById(R.id.listview_item_line_one);
            textView.setText(item.getDetails().getFriendlyName());
            return convertView;
        }
    }

    private class UpdateDeviceListTask extends AsyncTask<Void, Collection<Device>, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            final SystemManager deviceManager = SystemManager.getInstance();
            while (true) {
                Log.i(TAG, "Search devices");
                deviceManager.searchAllDevices();
                Collection<Device> devices = deviceManager.getDmrDevices();
                publishProgress(devices);
                if (isCancelled()) break;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupt update thread!");
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Collection<Device>... values) {
            Collection<Device> devices = values[0];
            if(devices.isEmpty()){
                Toast.makeText(getActivity(),"暂未发现可用设备",Toast.LENGTH_SHORT).show();
                dismiss();
            }else{
                mArrayAdapter.clear();
                mArrayAdapter.addAll(devices);
            }
            Log.i(TAG, "Device list update.");
        }
    }
}
