// Copyright 2024-present 650 Industries. All rights reserved.

import ContactsUI
import SwiftUI
import ExpoModulesCore

extension View {
  /**
   Applies the given transform if the given condition evaluates to `true`.
   - Parameters:
     - condition: The condition to evaluate.
     - transform: The transform to apply to the source `View`.
   - Returns: Either the original `View` or the modified `View` if the condition is `true`.
  */
  @ViewBuilder
  func `if`<Content: View>(_ condition: Bool, transform: (Self) -> Content) -> some View {
    if condition {
      transform(self)
    } else {
      self
    }
  }

  /**
   Applies the given transform if the given condition evaluates to `true`.
   - Parameters:
   - condition: The condition to evaluate.
   - transform: The transform to apply to the source `View`.
   - Returns: Either the original `View` or the modified `View` if the condition is `true`.
   */
  @ViewBuilder
  func `let`<Value, Content: View>(_ value: Value?, transform: (Self, Value) -> Content) -> some View {
    if let value {
      transform(self, value)
    } else {
      self
    }
  }
}

internal struct ExpoContactAccessButton: ExpoSwiftUI.View {
  @EnvironmentObject
  internal var props: ContactAccessButtonProps

  @State
  private var isPickerPresented = false

  var body: some View {
    if #available(iOS 18.0, *) {
      ContactAccessButton(
        queryString: props.query ?? "",
        ignoredEmails: Set(props.ignoredEmails ?? []),
        ignoredPhoneNumbers: Set(props.ignoredPhoneNumbers ?? []),
        approvalCallback: { _ in
          // TODO: Emit an event to JS
        }
      )
      .contactAccessButtonCaption(props.caption?.toContactAccessButtonCaption() ?? .defaultText)
      .padding(.all, props.padding)
      .tint(props.tintColor)
      .let(props.backgroundColor) { view, backgroundColor in
        view
          .background(backgroundColor)
          .backgroundStyle(backgroundColor)
      }
      .let(props.foregroundColor) { view, foregroundColor in
        view.foregroundStyle(foregroundColor)
      }
    }
  }
}
