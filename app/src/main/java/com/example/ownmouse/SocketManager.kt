package com.example.ownmouse

import android.util.Log
import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.Executors

object SocketManager {
    private var socket: Socket? = null
    private var writer: PrintWriter? = null
    private val executor = Executors.newSingleThreadExecutor()

    fun connect(serverIp: String, serverPort: Int, onResult: (Boolean, String) -> Unit) {
        executor.execute {
            try {
                socket = Socket(serverIp, serverPort)
                writer = PrintWriter(socket!!.getOutputStream(), true)
                onResult(true, "Connected to $serverIp:$serverPort")
            } catch (e: Exception) {
                Log.e("SocketManager", "Connection error", e)
                onResult(false, "Connection failed: ${e.message}")
            }
        }
    }

    fun sendCommand(cmd: String) {
        executor.execute {
            try {
                writer?.println(cmd)
                writer?.flush()
                Log.d("SocketManager", "Sent: $cmd")
            } catch (e: Exception) {
                Log.e("SocketManager", "Send failed", e)
            }
        }
    }

    fun close() {
        executor.execute {
            try {
                writer?.close()
                socket?.close()
                Log.d("SocketManager", "Socket closed")
            } catch (e: Exception) {
                Log.e("SocketManager", "Close error", e)
            }
        }
    }
}
