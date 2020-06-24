/** This class file was automatically generated by ASN1bean (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerInteger;
import com.beanit.asn1bean.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteNamedVariableListRequest implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private BerInteger scopeOfDelete = null;
  private ListOfVariableListName listOfVariableListName = null;
  private DomainName domainName = null;

  public DeleteNamedVariableListRequest() {}

  public DeleteNamedVariableListRequest(byte[] code) {
    this.code = code;
  }

  public BerInteger getScopeOfDelete() {
    return scopeOfDelete;
  }

  public void setScopeOfDelete(BerInteger scopeOfDelete) {
    this.scopeOfDelete = scopeOfDelete;
  }

  public ListOfVariableListName getListOfVariableListName() {
    return listOfVariableListName;
  }

  public void setListOfVariableListName(ListOfVariableListName listOfVariableListName) {
    this.listOfVariableListName = listOfVariableListName;
  }

  public DomainName getDomainName() {
    return domainName;
  }

  public void setDomainName(DomainName domainName) {
    this.domainName = domainName;
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
    int sublength;

    if (domainName != null) {
      sublength = domainName.encode(reverseOS);
      codeLength += sublength;
      codeLength += BerLength.encodeLength(reverseOS, sublength);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 2
      reverseOS.write(0xA2);
      codeLength += 1;
    }

    if (listOfVariableListName != null) {
      codeLength += listOfVariableListName.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
      reverseOS.write(0xA1);
      codeLength += 1;
    }

    if (scopeOfDelete != null) {
      codeLength += scopeOfDelete.encode(reverseOS, false);
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
    if (lengthVal == 0) {
      return tlByteCount;
    }
    vByteCount += berTag.decode(is);

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
      scopeOfDelete = new BerInteger();
      vByteCount += scopeOfDelete.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
      listOfVariableListName = new ListOfVariableListName();
      vByteCount += listOfVariableListName.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
      vByteCount += length.decode(is);
      domainName = new DomainName();
      vByteCount += domainName.decode(is, null);
      vByteCount += length.readEocIfIndefinite(is);
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
    boolean firstSelectedElement = true;
    if (scopeOfDelete != null) {
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("scopeOfDelete: ").append(scopeOfDelete);
      firstSelectedElement = false;
    }

    if (listOfVariableListName != null) {
      if (!firstSelectedElement) {
        sb.append(",\n");
      }
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("listOfVariableListName: ");
      listOfVariableListName.appendAsString(sb, indentLevel + 1);
      firstSelectedElement = false;
    }

    if (domainName != null) {
      if (!firstSelectedElement) {
        sb.append(",\n");
      }
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("domainName: ");
      domainName.appendAsString(sb, indentLevel + 1);
      firstSelectedElement = false;
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class ListOfVariableListName implements BerType, Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    private byte[] code = null;
    private List<ObjectName> seqOf = null;

    public ListOfVariableListName() {
      seqOf = new ArrayList<ObjectName>();
    }

    public ListOfVariableListName(byte[] code) {
      this.code = code;
    }

    public List<ObjectName> getObjectName() {
      if (seqOf == null) {
        seqOf = new ArrayList<ObjectName>();
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

        ObjectName element = new ObjectName();
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
        Iterator<ObjectName> it = seqOf.iterator();
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
  }

  public static class DomainName implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] code = null;
    private BasicIdentifier basic = null;

    public DomainName() {}

    public DomainName(byte[] code) {
      this.code = code;
    }

    public BasicIdentifier getBasic() {
      return basic;
    }

    public void setBasic(BasicIdentifier basic) {
      this.basic = basic;
    }

    @Override
    public int encode(OutputStream reverseOS) throws IOException {

      if (code != null) {
        reverseOS.write(code);
        return code.length;
      }

      int codeLength = 0;
      if (basic != null) {
        codeLength += basic.encode(reverseOS, true);
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

      if (berTag.equals(BasicIdentifier.tag)) {
        basic = new BasicIdentifier();
        tlvByteCount += basic.decode(is, false);
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

      if (basic != null) {
        sb.append("basic: ").append(basic);
        return;
      }

      sb.append("<none>");
    }
  }
}
