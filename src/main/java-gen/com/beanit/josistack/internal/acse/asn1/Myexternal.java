/** This class file was automatically generated by ASN1bean (http://www.beanit.com) */
package com.beanit.josistack.internal.acse.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerAny;
import com.beanit.asn1bean.ber.types.BerBitString;
import com.beanit.asn1bean.ber.types.BerInteger;
import com.beanit.asn1bean.ber.types.BerObjectIdentifier;
import com.beanit.asn1bean.ber.types.BerOctetString;
import com.beanit.asn1bean.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Myexternal implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 8);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private BerObjectIdentifier directReference = null;
  private BerInteger indirectReference = null;
  private Encoding encoding = null;

  public Myexternal() {}

  public Myexternal(byte[] code) {
    this.code = code;
  }

  public BerObjectIdentifier getDirectReference() {
    return directReference;
  }

  public void setDirectReference(BerObjectIdentifier directReference) {
    this.directReference = directReference;
  }

  public BerInteger getIndirectReference() {
    return indirectReference;
  }

  public void setIndirectReference(BerInteger indirectReference) {
    this.indirectReference = indirectReference;
  }

  public Encoding getEncoding() {
    return encoding;
  }

  public void setEncoding(Encoding encoding) {
    this.encoding = encoding;
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
    codeLength += encoding.encode(reverseOS);

    if (indirectReference != null) {
      codeLength += indirectReference.encode(reverseOS, true);
    }

    if (directReference != null) {
      codeLength += directReference.encode(reverseOS, true);
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
    vByteCount += berTag.decode(is);

    if (berTag.equals(BerObjectIdentifier.tag)) {
      directReference = new BerObjectIdentifier();
      vByteCount += directReference.decode(is, false);
      vByteCount += berTag.decode(is);
    }

    if (berTag.equals(BerInteger.tag)) {
      indirectReference = new BerInteger();
      vByteCount += indirectReference.decode(is, false);
      vByteCount += berTag.decode(is);
    }

    encoding = new Encoding();
    numDecodedBytes = encoding.decode(is, berTag);
    if (numDecodedBytes != 0) {
      vByteCount += numDecodedBytes;
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match mandatory sequence component.");
    }
    if (lengthVal < 0) {
      if (!berTag.equals(0, 0, 0)) {
        throw new IOException("Decoded sequence has wrong end of contents octets");
      }
      vByteCount += BerLength.readEocByte(is);
      return tlByteCount + vByteCount;
    }

    throw new IOException(
        "Unexpected end of sequence, length tag: " + lengthVal + ", bytes decoded: " + vByteCount);
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

    sb.append("{");
    boolean firstSelectedElement = true;
    if (directReference != null) {
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("directReference: ").append(directReference);
      firstSelectedElement = false;
    }

    if (indirectReference != null) {
      if (!firstSelectedElement) {
        sb.append(",\n");
      }
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("indirectReference: ").append(indirectReference);
      firstSelectedElement = false;
    }

    if (!firstSelectedElement) {
      sb.append(",\n");
    }
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (encoding != null) {
      sb.append("encoding: ");
      encoding.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("encoding: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class Encoding implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] code = null;
    private BerAny singleASN1Type = null;
    private BerOctetString octetAligned = null;
    private BerBitString arbitrary = null;

    public Encoding() {}

    public Encoding(byte[] code) {
      this.code = code;
    }

    public BerAny getSingleASN1Type() {
      return singleASN1Type;
    }

    public void setSingleASN1Type(BerAny singleASN1Type) {
      this.singleASN1Type = singleASN1Type;
    }

    public BerOctetString getOctetAligned() {
      return octetAligned;
    }

    public void setOctetAligned(BerOctetString octetAligned) {
      this.octetAligned = octetAligned;
    }

    public BerBitString getArbitrary() {
      return arbitrary;
    }

    public void setArbitrary(BerBitString arbitrary) {
      this.arbitrary = arbitrary;
    }

    @Override
    public int encode(OutputStream reverseOS) throws IOException {

      if (code != null) {
        reverseOS.write(code);
        return code.length;
      }

      int codeLength = 0;
      int sublength;

      if (arbitrary != null) {
        codeLength += arbitrary.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 2
        reverseOS.write(0x82);
        codeLength += 1;
        return codeLength;
      }

      if (octetAligned != null) {
        codeLength += octetAligned.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 1
        reverseOS.write(0x81);
        codeLength += 1;
        return codeLength;
      }

      if (singleASN1Type != null) {
        sublength = singleASN1Type.encode(reverseOS);
        codeLength += sublength;
        codeLength += BerLength.encodeLength(reverseOS, sublength);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
        reverseOS.write(0xA0);
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

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
        BerLength explicitTagLength = new BerLength();
        tlvByteCount += explicitTagLength.decode(is);
        singleASN1Type = new BerAny();
        tlvByteCount += singleASN1Type.decode(is, null);
        tlvByteCount += explicitTagLength.readEocIfIndefinite(is);
        return tlvByteCount;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
        octetAligned = new BerOctetString();
        tlvByteCount += octetAligned.decode(is, false);
        return tlvByteCount;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
        arbitrary = new BerBitString();
        tlvByteCount += arbitrary.decode(is, false);
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

      if (singleASN1Type != null) {
        sb.append("singleASN1Type: ").append(singleASN1Type);
        return;
      }

      if (octetAligned != null) {
        sb.append("octetAligned: ").append(octetAligned);
        return;
      }

      if (arbitrary != null) {
        sb.append("arbitrary: ").append(arbitrary);
        return;
      }

      sb.append("<none>");
    }
  }
}
