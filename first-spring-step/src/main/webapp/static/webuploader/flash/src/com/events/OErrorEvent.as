package com.events {
import flash.events.Event;

public class OErrorEvent extends Event {
    public static const ERROR:String = 'moxieerror';

    public function OErrorEvent(type:String, code:int = 0) {
        this.code = code;
        super(type, true, false);
    }

    public var code:uint;

    public override function clone():Event {
        return new OErrorEvent(type, code);
    }
}
}