package org.robovm.apple.uikit;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.*;

public class UITextField
        extends UIControl
        implements UITextInput, NSCoding
{
    public UITextField() {
    }

    @Override
    public UITextRange getSelectedTextRange() {
        return null;
    }

    @Override
    public void setSelectedTextRange(UITextRange uiTextRange) {

    }

    @Override
    public UITextRange getMarkedTextRange() {
        return null;
    }

    @Override
    public UITextInputTextStyle getMarkedTextStyle() {
        return null;
    }

    @Override
    public void setMarkedTextStyle(UITextInputTextStyle uiTextInputTextStyle) {

    }

    @Override
    public UITextPosition getBeginningOfDocument() {
        return null;
    }

    @Override
    public UITextPosition getEndOfDocument() {
        return null;
    }

    @Override
    public UITextInputDelegate getInputDelegate() {
        return null;
    }

    @Override
    public void setInputDelegate(UITextInputDelegate uiTextInputDelegate) {

    }

    @Override
    public UITextInputTokenizer getTokenizer() {
        return null;
    }

    @Override
    public UIView getTextInputView() {
        return null;
    }

    @Override
    public UITextStorageDirection getSelectionAffinity() {
        return null;
    }

    @Override
    public void setSelectionAffinity(UITextStorageDirection uiTextStorageDirection) {

    }

    @Override
    public String getText(UITextRange uiTextRange) {
        return null;
    }

    @Override
    public void replaceText(UITextRange uiTextRange, String s) {

    }

    @Override
    public void setMarkedText(String s, NSRange nsRange) {

    }

    @Override
    public void unmarkText() {

    }

    @Override
    public UITextRange getTextRange(UITextPosition uiTextPosition, UITextPosition uiTextPosition1) {
        return null;
    }

    @Override
    public UITextPosition getPosition(UITextPosition uiTextPosition, long l) {
        return null;
    }

    @Override
    public UITextPosition getPosition(UITextPosition uiTextPosition, UITextLayoutDirection uiTextLayoutDirection, long l) {
        return null;
    }

    @Override
    public NSComparisonResult comparePositions(UITextPosition uiTextPosition, UITextPosition uiTextPosition1) {
        return null;
    }

    @Override
    public long getOffset(UITextPosition uiTextPosition, UITextPosition uiTextPosition1) {
        return 0;
    }

    @Override
    public UITextPosition getPosition(UITextRange uiTextRange, UITextLayoutDirection uiTextLayoutDirection) {
        return null;
    }

    @Override
    public UITextRange getCharacterRange(UITextPosition uiTextPosition, UITextLayoutDirection uiTextLayoutDirection) {
        return null;
    }

    @Override
    public UITextWritingDirection getBaseWritingDirection(UITextPosition uiTextPosition, UITextStorageDirection uiTextStorageDirection) {
        return null;
    }

    @Override
    public void setBaseWritingDirection(UITextWritingDirection uiTextWritingDirection, UITextRange uiTextRange) {

    }

    @Override
    public CGRect getFirstRect(UITextRange uiTextRange) {
        return null;
    }

    @Override
    public CGRect getCaretRect(UITextPosition uiTextPosition) {
        return null;
    }

    @Override
    public NSArray<UITextSelectionRect> getSelectionRects(UITextRange uiTextRange) {
        return null;
    }

    @Override
    public UITextPosition getClosestPosition(CGPoint cgPoint) {
        return null;
    }

    @Override
    public UITextPosition getClosestPosition(CGPoint cgPoint, UITextRange uiTextRange) {
        return null;
    }

    @Override
    public UITextRange getCharacterRange(CGPoint cgPoint) {
        return null;
    }

    @Override
    public boolean shouldChangeText(UITextRange uiTextRange, String s) {
        return false;
    }

    @Override
    public UITextInputTextStyle getTextStyling(UITextPosition uiTextPosition, UITextStorageDirection uiTextStorageDirection) {
        return null;
    }

    @Override
    public UITextPosition getPosition(UITextRange uiTextRange, long l) {
        return null;
    }

    @Override
    public long getCharacterOffset(UITextPosition uiTextPosition, UITextRange uiTextRange) {
        return 0;
    }

    @Override
    public void insertDictationResult(NSArray<UIDictationPhrase> nsArray) {

    }

    @Override
    public void dictationRecordingDidEnd() {

    }

    @Override
    public void dictationRecognitionFailed() {

    }

    @Override
    public NSObject getInsertDictationResultPlaceholder() {
        return null;
    }

    @Override
    public CGRect getDictationResultPlaceholderFrame(NSObject nsObject) {
        return null;
    }

    @Override
    public void removeDictationResultPlaceholder(NSObject nsObject, boolean b) {

    }

    @Override
    public boolean hasText() {
        return false;
    }

    @Override
    public void insertText(String s) {

    }

    @Override
    public void deleteBackward() {

    }

    @Override
    public UITextAutocapitalizationType getAutocapitalizationType() {
        return null;
    }

    @Override
    public void setAutocapitalizationType(UITextAutocapitalizationType uiTextAutocapitalizationType) {

    }

    @Override
    public UITextAutocorrectionType getAutocorrectionType() {
        return null;
    }

    @Override
    public void setAutocorrectionType(UITextAutocorrectionType uiTextAutocorrectionType) {

    }

    @Override
    public UITextSpellCheckingType getSpellCheckingType() {
        return null;
    }

    @Override
    public void setSpellCheckingType(UITextSpellCheckingType uiTextSpellCheckingType) {

    }

    @Override
    public UIKeyboardType getKeyboardType() {
        return null;
    }

    @Override
    public void setKeyboardType(UIKeyboardType uiKeyboardType) {

    }

    @Override
    public UIKeyboardAppearance getKeyboardAppearance() {
        return null;
    }

    @Override
    public void setKeyboardAppearance(UIKeyboardAppearance uiKeyboardAppearance) {

    }

    @Override
    public UIReturnKeyType getReturnKeyType() {
        return null;
    }

    @Override
    public void setReturnKeyType(UIReturnKeyType uiReturnKeyType) {

    }

    @Override
    public boolean enablesReturnKeyAutomatically() {
        return false;
    }

    @Override
    public void setEnablesReturnKeyAutomatically(boolean b) {

    }

    @Override
    public boolean isSecureTextEntry() {
        return false;
    }

    @Override
    public void setSecureTextEntry(boolean b) {

    }

    public void setBackgroundColor(UIColor var1) {

    }

    public void setPlaceholder(String var1) {

    }
}
