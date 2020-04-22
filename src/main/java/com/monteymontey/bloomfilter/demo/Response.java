package com.monteymontey.bloomfilter.demo;


public class Response {
    public boolean isLeaked() {
        return leaked;
    }
    private final boolean leaked;

    public Response(boolean leaked) {
        this.leaked = leaked;
    }
}
