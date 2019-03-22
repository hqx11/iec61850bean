/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.josistack.internal.acse.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class RLREApdu implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.APPLICATION_CLASS, BerTag.CONSTRUCTED, 3);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private ReleaseResponseReason reason = null;
  private AssociationInformation userInformation = null;

  public RLREApdu() {}

  public RLREApdu(byte[] code) {
    this.code = code;
  }

  public ReleaseResponseReason getReason() {
    return reason;
  }

  public void setReason(ReleaseResponseReason reason) {
    this.reason = reason;
  }

  public AssociationInformation getUserInformation() {
    return userInformation;
  }

  public void setUserInformation(AssociationInformation userInformation) {
    this.userInformation = userInformation;
  }

  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      for (int i = code.length - 1; i >= 0; i--) {
        reverseOS.write(code[i]);
      }
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    if (userInformation != null) {
      codeLength += userInformation.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 30
      reverseOS.write(0xBE);
      codeLength += 1;
    }

    if (reason != null) {
      codeLength += reason.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, PRIMITIVE, 0
      reverseOS.write(0x80);
      codeLength += 1;
    }

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int codeLength = 0;
    int subCodeLength = 0;
    BerTag berTag = new BerTag();

    if (withTag) {
      codeLength += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    codeLength += length.decode(is);

    int totalLength = length.val;
    codeLength += totalLength;

    if (totalLength == 0) {
      return codeLength;
    }
    subCodeLength += berTag.decode(is);
    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
      reason = new ReleaseResponseReason();
      subCodeLength += reason.decode(is, false);
      if (subCodeLength == totalLength) {
        return codeLength;
      }
      subCodeLength += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 30)) {
      userInformation = new AssociationInformation();
      subCodeLength += userInformation.decode(is, false);
      if (subCodeLength == totalLength) {
        return codeLength;
      }
    }
    throw new IOException(
        "Unexpected end of sequence, length tag: "
            + totalLength
            + ", actual sequence length: "
            + subCodeLength);
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{");
    boolean firstSelectedElement = true;
    if (reason != null) {
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("reason: ").append(reason);
      firstSelectedElement = false;
    }

    if (userInformation != null) {
      if (!firstSelectedElement) {
        sb.append(",\n");
      }
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("userInformation: ");
      userInformation.appendAsString(sb, indentLevel + 1);
      firstSelectedElement = false;
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }
}