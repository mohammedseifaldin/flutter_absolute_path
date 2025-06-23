package `in`.canews.flutter_absolute_path

import androidx.annotation.NonNull
import android.content.Context
import android.net.Uri
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import java.io.File

class FlutterAbsolutePathPlugin : FlutterPlugin, MethodCallHandler {
  private lateinit var context: Context
  private lateinit var channel: MethodChannel

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    context = binding.applicationContext
    channel = MethodChannel(binding.binaryMessenger, "flutter_absolute_path")
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    if (call.method == "getAbsolutePath") {
      val uriString = call.argument<Any>("uri") as String
      val uri = Uri.parse(uriString)
      result.success(FileDirectory.getAbsolutePath(context, uri))
    } else {
      result.notImplemented()
    }
  }
}
