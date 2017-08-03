package com.events {
import flash.events.Event;

public class ImageEvent extends Event {
    public static const RESIZE:String = 'imageresize';

    public function ImageEvent(type:String, data:* = null) {
        this.data = data;
        super(type, bubbles, cancelable);
    }

    public var data:*;

    public override function clone():Event {
        return new ImageEvent(type, data);
    }
}
}