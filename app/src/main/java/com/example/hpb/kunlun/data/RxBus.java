package com.example.hpb.kunlun.data;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

  private RxBus() {

  }

  private static class Holder {
    public static RxBus rxBus = new RxBus();
  }

  public static RxBus getInstance() {
    return Holder.rxBus;
  }

  private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

  public void send(Object o) {
    _bus.onNext(o);
  }

  public Observable<Object> toObserverable() {
    return _bus;
  }

  public boolean hasObservers() {
    return _bus.hasObservers();
  }
}