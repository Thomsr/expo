import { requireNativeView } from 'expo';
import { Platform, requireOptionalNativeModule } from 'expo-modules-core';
import React from 'react';
import { ViewProps } from 'react-native';

type ContactAccessButtonProps = ViewProps & {
  /**
   * A string to match against contacts not yet exposed to the app.
   * You typically get this value from a search UI that your app presents, like a text field.
   */
  query?: string;

  /**
   * A tint color of the button.
   */
  tintColor?: string;

  /**
   * When the query produces a single result, the contact access button shows the caption under the matching contact name.
   * It can be nothing (default), email address or phone number.
   */
  caption?: 'default' | 'email' | 'phone';

  /**
   * An array of email addresses. The search omits contacts matching query that also match any email address in this array.
   */
  ignoredEmails?: string[];

  /**
   * An array of phone numbers. The search omits contacts matching query that also match any phone number in this set.
   */
  ignoredPhoneNumbers?: string[];
};

type ContactAccessButtonModule = {
  /**
   * Boolean value whether the contact access button is available on this platform.
   */
  isAvailable: boolean;
};

const NativeContactAccessButton =
  requireNativeView<ContactAccessButtonProps>('ExpoContactAccessButton');

/**
 * Creates a contact access button to quickly add contacts under limited-access authorization.
 */
export default class ContactAccessButton extends React.PureComponent<ContactAccessButtonProps> {
  static isAvailable() {
    return (
      Platform.OS === 'ios' &&
      requireOptionalNativeModule<ContactAccessButtonModule>('ExpoContactAccessButton')?.isAvailable
    );
  }

  render() {
    if (Platform.OS !== 'ios') {
      return null;
    }
    return <NativeContactAccessButton {...this.props} />;
  }
}
