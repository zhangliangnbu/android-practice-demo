// IBookManager.aidl
package com.liang.androidskilldemo.service.aidl;
import com.liang.androidskilldemo.service.aidl.Book;
import com.liang.androidskilldemo.service.aidl.IOnBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   List<Book> getBookList();
   void addBook(in Book book);
   void registerOnBookArrivedListener(in IOnBookArrivedListener onBookArrivedListener);
   void unregisterOnBookArrivedListener(in IOnBookArrivedListener onBookArrivedListener);
}
