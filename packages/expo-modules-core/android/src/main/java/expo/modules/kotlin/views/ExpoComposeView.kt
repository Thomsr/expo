@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package expo.modules.kotlin.views

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.annotation.UiThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import com.facebook.react.uimanager.BackgroundStyleApplicator
import expo.modules.kotlin.AppContext
import androidx.compose.material3.*

import android.graphics.Color;
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.WindowRecomposerPolicy
import androidx.compose.ui.platform.compositionContext
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner


/**
 * A base class that should be used by compose views.
 */
abstract class ExpoComposeView(
  context: Context,
  appContext: AppContext
) : ExpoView(context, appContext) {
  open val props: Any? = Any()

  @OptIn(InternalComposeUiApi::class)
  val layout = ComposeView(context).also {
    it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//it.clipChildren = false
//    it.clipChildren = false
//    it.clipToPadding = false
//    it.clipToOutline = false
    val root = appContext.activityProvider?.currentActivity?.findViewById<View>(android.R.id.content)
    if(root != null) {
      WindowRecomposerPolicy.createAndInstallWindowRecomposer(root)
      it.setParentCompositionContext(root.compositionContext)
      it.setViewTreeLifecycleOwner(root.findViewTreeLifecycleOwner())
    }
    addView(it)

  }

   fun setContent(
    content: @Composable () -> Unit
  ) {
    layout.setContent { content() }
   }
}
