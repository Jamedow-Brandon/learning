package com.events {
import flash.events.Event;

public class FilePickerEvent extends Event {
    public static const SELECT:String = 'filepickerselect';
    public static const CANCEL:String = 'filepickercancel';

    public function FilePickerEvent(type:String, data:* = false) {
        this.data = data;
        super(type, false, false);
    }

    public var data:*;

}
}