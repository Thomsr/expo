package expo.modules.jetpackcomposetest

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoComposeView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon

data class ExpoJetpackComposeTestProps(
  val text: MutableState<String> = mutableStateOf("")
)

class ExpoJetpackComposeTestView(context: Context, appContext: AppContext) : ExpoComposeView(context, appContext) {
  override val props = ExpoJetpackComposeTestProps()
  val onButtonPress by EventDispatcher()

  @Composable
  fun ExtendedFAB() {
    val text by remember { props.text }
    ExtendedFloatingActionButton(
      onClick = { onButtonPress(mapOf()) },
      icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
      text = { Text(text = text) },
    )
  }

  init {
    clipChildren = false
    clipToPadding = false
    setContent { ExtendedFAB() }
  }
}

