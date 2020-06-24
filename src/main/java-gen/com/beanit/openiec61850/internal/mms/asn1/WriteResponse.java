/** This class file was automatically generated by ASN1bean (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerNull;
import com.beanit.asn1bean.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WriteResponse implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private List<CHOICE> seqOf = null;

  public WriteResponse() {
    seqOf = new ArrayList<CHOICE>();
  }

  public WriteResponse(byte[] code) {
    this.code = code;
  }

  public List<CHOICE> getCHOICE() {
    if (seqOf == null) {
      seqOf = new ArrayList<CHOICE>();
    }
    return seqOf;
  }

  @Override
  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      reverseOS.write(code);
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    for (int i = (seqOf.size() - 1); i >= 0; i--) {
      codeLength += seqOf.get(i).encode(reverseOS);
    }

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  @Override
  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int tlByteCount = 0;
    int vByteCount = 0;
    int numDecodedBytes;
    BerTag berTag = new BerTag();
    if (withTag) {
      tlByteCount += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    tlByteCount += length.decode(is);
    int lengthVal = length.val;

    while (vByteCount < lengthVal || lengthVal < 0) {
      vByteCount += berTag.decode(is);

      if (lengthVal < 0 && berTag.equals(0, 0, 0)) {
        vByteCount += BerLength.readEocByte(is);
        break;
      }

      CHOICE element = new CHOICE();
      numDecodedBytes = element.decode(is, berTag);
      if (numDecodedBytes == 0) {
        throw new IOException("Tag did not match");
      }
      vByteCount += numDecodedBytes;
      seqOf.add(element);
    }
    if (lengthVal >= 0 && vByteCount != lengthVal) {
      throw new IOException(
          "Decoded SequenceOf or SetOf has wrong length. Expected "
              + lengthVal
              + " but has "
              + vByteCount);
    }
    return tlByteCount + vByteCount;
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (seqOf == null) {
      sb.append("null");
    } else {
      Iterator<CHOICE> it = seqOf.iterator();
      if (it.hasNext()) {
        it.next().appendAsString(sb, indentLevel + 1);
        while (it.hasNext()) {
          sb.append(",\n");
          for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
          }
          it.next().appendAsString(sb, indentLevel + 1);
        }
      }
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class CHOICE implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] code = null;
    private DataAccessError failure = null;
    private BerNull success = null;

    public CHOICE() {}

    public CHOICE(byte[] code) {
      this.code = code;
    }

    public DataAccessError getFailure() {
      return failure;
    }

    public void setFailure(DataAccessError failure) {
      this.failure = failure;
    }

    public BerNull getSuccess() {
      return success;
    }

    public void setSuccess(BerNull success) {
      this.success = success;
    }

    @Override
    public int encode(OutputStream reverseOS) throws IOException {

      if (code != null) {
        reverseOS.write(code);
        return code.length;
      }

      int codeLength = 0;
      if (success != null) {
        codeLength += success.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 1
        reverseOS.write(0x81);
        codeLength += 1;
        return codeLength;
      }

      if (failure != null) {
        codeLength += failure.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 0
        reverseOS.write(0x80);
        codeLength += 1;
        return codeLength;
      }

      throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
    }

    @Override
    public int decode(InputStream is) throws IOException {
      return decode(is, null);
    }

    public int decode(InputStream is, BerTag berTag) throws IOException {

      int tlvByteCount = 0;
      boolean tagWasPassed = (berTag != null);

      if (berTag == null) {
        berTag = new BerTag();
        tlvByteCount += berTag.decode(is);
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
        failure = new DataAccessError();
        tlvByteCount += failure.decode(is, false);
        return tlvByteCount;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
        success = new BerNull();
        tlvByteCount += success.decode(is, false);
        return tlvByteCount;
      }

      if (tagWasPassed) {
        return 0;
      }

      throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
      ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
      encode(reverseOS);
      code = reverseOS.getArray();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      appendAsString(sb, 0);
      return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

      if (failure != null) {
        sb.append("failure: ").append(failure);
        return;
      }

      if (success != null) {
        sb.append("success: ").append(success);
        return;
      }

      sb.append("<none>");
    }
  }
}
