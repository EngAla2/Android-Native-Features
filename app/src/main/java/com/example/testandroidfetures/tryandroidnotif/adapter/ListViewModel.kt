package com.example.testandroidfetures.tryandroidnotif.adapter

class  ListViewModel{
    var id: Int? = null
    var channel_id: String? = null
    var allow_channel: Boolean? = null

    constructor(id: Int, _channel_id: String, _allow_channel: Boolean) {
        this.id = id
        this.channel_id = _channel_id
        this.allow_channel = _allow_channel
    }
}