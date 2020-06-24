/** This class file was automatically generated by ASN1bean (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerType;
import com.beanit.openiec61850.internal.BerBoolean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetNameListResponse implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private ListOfIdentifier listOfIdentifier = null;
  private BerBoolean moreFollows = null;

  public GetNameListResponse() {}

  public GetNameListResponse(byte[] code) {
    this.code = code;
  }

  public ListOfIdentifier getListOfIdentifier() {
    return listOfIdentifier;
  }

  public void setListOfIdentifier(ListOfIdentifier listOfIdentifier) {
    this.listOfIdentifier = listOfIdentifier;
  }

  public BerBoolean getMoreFollows() {
    return moreFollows;
  }

  public void setMoreFollows(BerBoolean moreFollows) {
    this.moreFollows = moreFollows;
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
    if (moreFollows != null) {
      codeLength += moreFollows.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, PRIMITIVE, 1
      reverseOS.write(0x81);
      codeLength += 1;
    }

    codeLength += listOfIdentifier.encode(reverseOS, false);
    // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
    reverseOS.write(0xA0);
    codeLength += 1;

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
    BerTag berTag = new BerTag();

    if (withTag) {
      tlByteCount += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    tlByteCount += length.decode(is);
    int lengthVal = length.val;
    vByteCount += berTag.decode(is);

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      listOfIdentifier = new ListOfIdentifier();
      vByteCount += listOfIdentifier.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match mandatory sequence component.");
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
      moreFollows = new BerBoolean();
      vByteCount += moreFollows.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
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
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (listOfIdentifier != null) {
      sb.append("listOfIdentifier: ");
      listOfIdentifier.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("listOfIdentifier: <empty-required-field>");
    }

    if (moreFollows != null) {
      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("moreFollows: ").append(moreFollows);
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class ListOfIdentifier implements BerType, Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    private byte[] code = null;
    private List<Identifier> seqOf = null;

    public ListOfIdentifier() {
      seqOf = new ArrayList<Identifier>();
    }

    public ListOfIdentifier(byte[] code) {
      this.code = code;
    }

    public List<Identifier> getIdentifier() {
      if (seqOf == null) {
        seqOf = new ArrayList<Identifier>();
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
        codeLength += seqOf.get(i).encode(reverseOS, true);
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

        if (!berTag.equals(Identifier.tag)) {
          throw new IOException("Tag does not match mandatory sequence of/set of component.");
        }
        Identifier element = new Identifier();
        vByteCount += element.decode(is, false);
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
        Iterator<Identifier> it = seqOf.iterator();
        if (it.hasNext()) {
          sb.append(it.next());
          while (it.hasNext()) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
              sb.append("\t");
            }
            sb.append(it.next());
          }
        }
      }

      sb.append("\n");
      for (int i = 0; i < indentLevel; i++) {
        sb.append("\t");
      }
      sb.append("}");
    }
  }
}
