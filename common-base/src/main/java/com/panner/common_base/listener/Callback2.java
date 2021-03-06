package com.panner.common_base.listener;

/**
 * 通用回调
 * @author panzhijie
 * @version 2018-07-21 19:55
 */
public interface Callback2<T> {
    /**
     * 开始
     */
    void onPreExecute();

    /**
     * 结束
     */
    void onPostExecute();

    /**
     * 成功
     * @param t
     */
    void onSuccess(T t);

    /**
     * 失败
     * @param e
     */
    void onError(Throwable e);

    /**
     * 完成
     */
    void onFinish();

    /**
     * 无操作通用回调
     * @param <T>
     */
    class EmptyCallback<T> implements Callback2<T> {
        @Override
        public void onPreExecute() {

        }

        @Override
        public void onPostExecute() {

        }

        @Override
        public void onSuccess(T t) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onFinish() {

        }
    }
}
