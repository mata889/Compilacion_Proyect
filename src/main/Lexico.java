// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/main/Lexico.flex

package main;

import java_cup.runtime.Symbol;

// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
class Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int BLOCK_COMMENT = 2;
  public static final int COMMENT_LINE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\3\1\4\22\0\1\5\1\0"+
    "\1\6\1\7\2\0\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\12\22\1\23\1\24"+
    "\1\25\1\26\1\27\2\0\1\30\2\31\1\32\1\31"+
    "\1\33\15\31\1\34\6\31\1\35\1\0\1\36\1\0"+
    "\1\31\1\0\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\31\1\45\1\46\1\31\1\47\1\50\1\51\1\52"+
    "\1\53\1\54\1\31\1\55\1\56\1\57\1\60\1\61"+
    "\1\62\1\31\1\63\1\31\1\0\1\64\1\0\1\65"+
    "\6\0\1\3\u01a2\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\4\1\1\4\1\5\1\6"+
    "\1\7\1\10\1\7\1\11\1\12\1\1\1\13\1\14"+
    "\1\15\1\16\4\17\1\20\1\21\16\17\1\1\1\22"+
    "\2\23\1\2\1\24\1\0\1\25\1\26\1\27\1\0"+
    "\1\30\1\0\14\17\1\31\1\32\13\17\1\33\1\34"+
    "\10\17\1\35\1\17\1\36\1\37\3\17\1\40\1\41"+
    "\3\17\1\42\1\17\1\43\2\17\1\44\1\17\1\45"+
    "\1\46\2\17\1\47\1\50\1\17\1\51\3\17\1\52"+
    "\1\53\1\54\1\55\1\56\2\17\1\57\1\60\1\61"+
    "\3\17\1\62\1\63\2\17\1\64";

  private static int [] zzUnpackAction() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\66\0\154\0\242\0\330\0\242\0\u010e\0\u0144"+
    "\0\u017a\0\u01b0\0\242\0\242\0\242\0\242\0\242\0\u01e6"+
    "\0\242\0\u021c\0\u0252\0\242\0\u0288\0\u02be\0\u02f4\0\u032a"+
    "\0\u0360\0\u0396\0\u03cc\0\242\0\242\0\u0402\0\u0438\0\u046e"+
    "\0\u04a4\0\u04da\0\u0510\0\u0546\0\u057c\0\u05b2\0\u05e8\0\u061e"+
    "\0\u0654\0\u068a\0\u06c0\0\u06f6\0\242\0\242\0\u072c\0\242"+
    "\0\242\0\u010e\0\u010e\0\242\0\242\0\u0762\0\242\0\u0798"+
    "\0\u07ce\0\u0804\0\u083a\0\u0870\0\u08a6\0\u08dc\0\u0912\0\u0948"+
    "\0\u097e\0\u09b4\0\u09ea\0\u0a20\0\u0360\0\u0360\0\u0a56\0\u0a8c"+
    "\0\u0ac2\0\u0af8\0\u0b2e\0\u0b64\0\u0b9a\0\u0bd0\0\u0c06\0\u0c3c"+
    "\0\u0c72\0\242\0\242\0\u0ca8\0\u0cde\0\u0d14\0\u0d4a\0\u0d80"+
    "\0\u0db6\0\u0dec\0\u0e22\0\u0360\0\u0e58\0\u0360\0\u0360\0\u0e8e"+
    "\0\u0ec4\0\u0efa\0\u0360\0\u0360\0\u0f30\0\u0f66\0\u0f9c\0\u0360"+
    "\0\u0fd2\0\u0360\0\u1008\0\u103e\0\u0360\0\u1074\0\u0360\0\u0360"+
    "\0\u10aa\0\u10e0\0\u0360\0\u0360\0\u1116\0\u0360\0\u114c\0\u1182"+
    "\0\u11b8\0\u0360\0\u0360\0\u0360\0\u0360\0\u0360\0\u11ee\0\u1224"+
    "\0\u0360\0\u0360\0\u125a\0\u1290\0\u12c6\0\u12fc\0\u0360\0\242"+
    "\0\u1332\0\u1368\0\u0360";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\2\5\1\6\2\5\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\15"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\2\31"+
    "\1\32\1\33\1\34\1\35\1\31\1\36\1\37\1\40"+
    "\1\41\1\42\1\31\1\43\1\31\1\44\1\45\1\46"+
    "\2\31\1\47\1\50\1\51\1\31\1\52\1\53\1\31"+
    "\1\54\1\55\2\56\3\6\14\56\1\57\44\56\2\60"+
    "\1\61\2\6\61\60\67\0\2\5\1\0\2\5\60\0"+
    "\2\62\3\0\1\62\1\63\57\62\7\0\1\64\11\0"+
    "\1\65\54\0\1\55\105\0\5\66\2\0\25\66\31\0"+
    "\1\67\60\0\1\22\71\0\2\55\61\0\1\55\2\0"+
    "\1\55\60\0\1\70\72\0\1\55\61\0\1\31\5\0"+
    "\5\31\2\0\16\31\1\71\6\31\24\0\1\31\5\0"+
    "\5\31\2\0\25\31\24\0\1\31\5\0\5\31\2\0"+
    "\1\72\24\31\24\0\1\31\5\0\5\31\2\0\16\31"+
    "\1\73\6\31\24\0\1\31\5\0\5\31\2\0\11\31"+
    "\1\74\2\31\1\75\10\31\24\0\1\31\5\0\5\31"+
    "\2\0\1\76\24\31\24\0\1\31\5\0\5\31\2\0"+
    "\4\31\1\77\20\31\24\0\1\31\5\0\5\31\2\0"+
    "\7\31\1\100\1\31\1\101\1\31\1\102\11\31\24\0"+
    "\1\31\5\0\5\31\2\0\14\31\1\103\4\31\1\104"+
    "\3\31\24\0\1\31\5\0\5\31\2\0\5\31\1\105"+
    "\5\31\1\106\11\31\24\0\1\31\5\0\5\31\2\0"+
    "\4\31\1\107\20\31\24\0\1\31\5\0\5\31\2\0"+
    "\1\110\24\31\24\0\1\31\5\0\5\31\2\0\4\31"+
    "\1\111\14\31\1\112\3\31\24\0\1\31\5\0\5\31"+
    "\2\0\4\31\1\113\20\31\24\0\1\31\5\0\5\31"+
    "\2\0\20\31\1\114\4\31\24\0\1\31\5\0\5\31"+
    "\2\0\6\31\1\115\16\31\24\0\1\31\5\0\5\31"+
    "\2\0\1\116\13\31\1\117\10\31\24\0\1\31\5\0"+
    "\5\31\2\0\11\31\1\120\2\31\1\121\10\31\66\0"+
    "\1\55\10\0\1\61\67\0\1\122\102\0\1\123\61\0"+
    "\1\31\5\0\5\31\2\0\16\31\1\124\6\31\24\0"+
    "\1\31\5\0\5\31\2\0\11\31\1\125\13\31\24\0"+
    "\1\31\5\0\5\31\2\0\21\31\1\126\3\31\24\0"+
    "\1\31\5\0\5\31\2\0\14\31\1\127\10\31\24\0"+
    "\1\31\5\0\5\31\2\0\14\31\1\130\10\31\24\0"+
    "\1\31\5\0\5\31\2\0\17\31\1\131\1\132\4\31"+
    "\24\0\1\31\5\0\5\31\2\0\5\31\1\133\17\31"+
    "\24\0\1\31\5\0\5\31\2\0\5\31\1\134\17\31"+
    "\24\0\1\31\5\0\5\31\2\0\17\31\1\135\5\31"+
    "\24\0\1\31\5\0\5\31\2\0\3\31\1\136\21\31"+
    "\24\0\1\31\5\0\5\31\2\0\16\31\1\137\6\31"+
    "\24\0\1\31\5\0\5\31\2\0\13\31\1\140\11\31"+
    "\24\0\1\31\5\0\5\31\2\0\20\31\1\141\4\31"+
    "\24\0\1\31\5\0\5\31\2\0\7\31\1\142\15\31"+
    "\24\0\1\31\5\0\5\31\2\0\23\31\1\143\1\31"+
    "\24\0\1\31\5\0\5\31\2\0\12\31\1\144\12\31"+
    "\24\0\1\31\5\0\5\31\2\0\15\31\1\145\7\31"+
    "\24\0\1\31\5\0\5\31\2\0\1\146\24\31\24\0"+
    "\1\31\5\0\5\31\2\0\16\31\1\147\6\31\24\0"+
    "\1\31\5\0\5\31\2\0\16\31\1\150\6\31\24\0"+
    "\1\31\5\0\5\31\2\0\7\31\1\151\15\31\24\0"+
    "\1\31\5\0\5\31\2\0\4\31\1\152\20\31\24\0"+
    "\1\31\5\0\5\31\2\0\16\31\1\153\6\31\24\0"+
    "\1\31\5\0\5\31\2\0\1\154\24\31\24\0\1\31"+
    "\5\0\5\31\2\0\17\31\1\126\5\31\24\0\1\31"+
    "\5\0\5\31\2\0\4\31\1\155\20\31\24\0\1\31"+
    "\5\0\5\31\2\0\2\31\1\156\22\31\24\0\1\31"+
    "\5\0\5\31\2\0\11\31\1\157\13\31\24\0\1\31"+
    "\5\0\5\31\2\0\4\31\1\160\20\31\24\0\1\31"+
    "\5\0\5\31\2\0\2\31\1\161\22\31\24\0\1\31"+
    "\5\0\5\31\2\0\1\162\24\31\24\0\1\31\5\0"+
    "\5\31\2\0\4\31\1\163\20\31\24\0\1\31\5\0"+
    "\5\31\2\0\2\31\1\164\22\31\24\0\1\31\5\0"+
    "\5\31\2\0\20\31\1\165\4\31\24\0\1\31\5\0"+
    "\5\31\2\0\13\31\1\166\11\31\24\0\1\31\5\0"+
    "\5\31\2\0\11\31\1\167\13\31\24\0\1\31\5\0"+
    "\5\31\2\0\16\31\1\170\6\31\24\0\1\31\5\0"+
    "\5\31\2\0\14\31\1\171\10\31\24\0\1\31\5\0"+
    "\5\31\2\0\3\31\1\172\21\31\24\0\1\31\5\0"+
    "\5\31\2\0\3\31\1\173\21\31\24\0\1\31\5\0"+
    "\5\31\2\0\24\31\1\174\24\0\1\31\5\0\5\31"+
    "\2\0\10\31\1\175\14\31\24\0\1\31\5\0\5\31"+
    "\2\0\6\31\1\176\16\31\24\0\1\31\5\0\5\31"+
    "\2\0\21\31\1\177\3\31\24\0\1\31\5\0\5\31"+
    "\2\0\4\31\1\200\20\31\24\0\1\31\5\0\5\31"+
    "\2\0\24\31\1\201\24\0\1\31\5\0\5\31\2\0"+
    "\20\31\1\202\4\31\24\0\1\31\5\0\5\31\2\0"+
    "\23\31\1\203\1\31\24\0\1\31\5\0\5\31\2\0"+
    "\11\31\1\204\13\31\24\0\1\31\5\0\5\31\2\0"+
    "\16\31\1\205\6\31\24\0\1\31\5\0\2\31\1\206"+
    "\2\31\2\0\25\31\24\0\1\31\5\0\5\31\2\0"+
    "\20\31\1\207\4\31\7\0\1\210\14\0\1\31\5\0"+
    "\5\31\2\0\25\31\24\0\1\31\5\0\5\31\2\0"+
    "\14\31\1\211\10\31\24\0\1\31\5\0\5\31\2\0"+
    "\23\31\1\212\1\31\24\0\1\31\5\0\5\31\2\0"+
    "\13\31\1\213\11\31\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5022];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\1\1\1\11\4\1\5\11\1\1\1\11"+
    "\2\1\1\11\7\1\2\11\17\1\2\11\1\1\2\11"+
    "\1\0\1\1\2\11\1\0\1\11\1\0\31\1\2\11"+
    "\64\1\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    String str = "";
    static int errores=0;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.out.println("Falla en lexico, char o variable no aceptada: " +yytext()+" Linea: "+(yyline + 1)+ ", Columna: "+(yycolumn+1));
							errores++;
            }
            // fall through
          case 53: break;
          case 2:
            { 
            }
            // fall through
          case 54: break;
          case 3:
            { System.out.print(yytext());
            }
            // fall through
          case 55: break;
          case 4:
            { return new Symbol(sym.PARENTESISA,yycolumn,yyline,yytext());
            }
            // fall through
          case 56: break;
          case 5:
            { return new Symbol(sym.PARENTESISC,yycolumn,yyline,yytext());
            }
            // fall through
          case 57: break;
          case 6:
            { return new Symbol(sym.OPEA_MULT,yycolumn,yyline,yytext());
            }
            // fall through
          case 58: break;
          case 7:
            { return new Symbol(sym.OPEA_SUM,yycolumn,yyline,yytext());
            }
            // fall through
          case 59: break;
          case 8:
            { return new Symbol(sym.COMMA,yycolumn,yyline,yytext());
            }
            // fall through
          case 60: break;
          case 9:
            { return new Symbol(sym.PUNTO,yycolumn,yyline,yytext());
            }
            // fall through
          case 61: break;
          case 10:
            { return new Symbol(sym.NUM,yycolumn,yyline,yytext());
            }
            // fall through
          case 62: break;
          case 11:
            { return new Symbol(sym.PUNTOC,yycolumn,yyline,yytext());
            }
            // fall through
          case 63: break;
          case 12:
            { return new Symbol(sym.ABRIRC,yycolumn,yyline,yytext());
            }
            // fall through
          case 64: break;
          case 13:
            { return new Symbol(sym.ASSIGNMENT,yycolumn,yyline,yytext());
            }
            // fall through
          case 65: break;
          case 14:
            { return new Symbol(sym.CERRARC,yycolumn,yyline,yytext());
            }
            // fall through
          case 66: break;
          case 15:
            { return new Symbol(sym.ID,yycolumn,yyline,yytext());
            }
            // fall through
          case 67: break;
          case 16:
            { return new Symbol(sym.BRACKETA,yycolumn,yyline,yytext());
            }
            // fall through
          case 68: break;
          case 17:
            { return new Symbol(sym.BRACKETC,yycolumn,yyline,yytext());
            }
            // fall through
          case 69: break;
          case 18:
            { return new Symbol(sym.OPER,yycolumn,yyline,yytext());
            }
            // fall through
          case 70: break;
          case 19:
            { /* do nothing */
            }
            // fall through
          case 71: break;
          case 20:
            { yybegin(YYINITIAL);
            }
            // fall through
          case 72: break;
          case 21:
            { return new Symbol(sym.CADENA,yycolumn,yyline,yytext());
            }
            // fall through
          case 73: break;
          case 22:
            { yybegin(COMMENT_LINE);
            }
            // fall through
          case 74: break;
          case 23:
            { yybegin(BLOCK_COMMENT);
            }
            // fall through
          case 75: break;
          case 24:
            { return new Symbol(sym.FLECHA,yycolumn,yyline,yytext());
            }
            // fall through
          case 76: break;
          case 25:
            { return new Symbol(sym.IF,yycolumn,yyline,yytext());
            }
            // fall through
          case 77: break;
          case 26:
            { return new Symbol(sym.IN,yycolumn,yyline,yytext());
            }
            // fall through
          case 78: break;
          case 27:
            { return new Symbol(sym.LETTER,yycolumn,yyline,yytext());
            }
            // fall through
          case 79: break;
          case 28:
            { return new Symbol(sym.NOT,yycolumn,yyline,yytext());
            }
            // fall through
          case 80: break;
          case 29:
            { return new Symbol(sym.EIF,yycolumn,yyline,yytext());
            }
            // fall through
          case 81: break;
          case 30:
            { return new Symbol(sym.END,yycolumn,yyline,yytext());
            }
            // fall through
          case 82: break;
          case 31:
            { return new Symbol(sym.FOR,yycolumn,yyline,yytext());
            }
            // fall through
          case 83: break;
          case 32:
            { return new Symbol(sym.NEW,yycolumn,yyline,yytext());
            }
            // fall through
          case 84: break;
          case 33:
            { return new Symbol(sym.NUMERITO,yycolumn,yyline,yytext());
            }
            // fall through
          case 85: break;
          case 34:
            { return new Symbol(sym.VAR,yycolumn,yyline,yytext());
            }
            // fall through
          case 86: break;
          case 35:
            { return new Symbol(sym.WLE,yycolumn,yyline,yytext());
            }
            // fall through
          case 87: break;
          case 36:
            { return new Symbol(sym.BOOL,yycolumn,yyline,yytext());
            }
            // fall through
          case 88: break;
          case 37:
            { return new Symbol(sym.VERDAD,yycolumn,yyline,yytext());
            }
            // fall through
          case 89: break;
          case 38:
            { return new Symbol(sym.CASE,yycolumn,yyline,yytext());
            }
            // fall through
          case 90: break;
          case 39:
            { return new Symbol(sym.ELSE,yycolumn,yyline,yytext());
            }
            // fall through
          case 91: break;
          case 40:
            { return new Symbol(sym.FUNC,yycolumn,yyline,yytext());
            }
            // fall through
          case 92: break;
          case 41:
            { return new Symbol(sym.MAIN,yycolumn,yyline,yytext());
            }
            // fall through
          case 93: break;
          case 42:
            { return new Symbol(sym.VOID,yycolumn,yyline,yytext());
            }
            // fall through
          case 94: break;
          case 43:
            { return new Symbol(sym.WORD,yycolumn,yyline,yytext());
            }
            // fall through
          case 95: break;
          case 44:
            { return new Symbol(sym.ARRAY,yycolumn,yyline,yytext());
            }
            // fall through
          case 96: break;
          case 45:
            { return new Symbol(sym.BLOCK,yycolumn,yyline,yytext());
            }
            // fall through
          case 97: break;
          case 46:
            { return new Symbol(sym.CATCH,yycolumn,yyline,yytext());
            }
            // fall through
          case 98: break;
          case 47:
            { return new Symbol(sym.REPLY,yycolumn,yyline,yytext());
            }
            // fall through
          case 99: break;
          case 48:
            { return new Symbol(sym.START,yycolumn,yyline,yytext());
            }
            // fall through
          case 100: break;
          case 49:
            { return new Symbol(sym.THROW,yycolumn,yyline,yytext());
            }
            // fall through
          case 101: break;
          case 50:
            { return new Symbol(sym.DEFAULT,yycolumn,yyline,yytext());
            }
            // fall through
          case 102: break;
          case 51:
            { return new Symbol(sym.LETRA,yycolumn,yyline,yytext());
            }
            // fall through
          case 103: break;
          case 52:
            { return new Symbol(sym.THROWDOWN,yycolumn,yyline,yytext());
            }
            // fall through
          case 104: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexico [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Lexico scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Lexico(reader);
          while ( !scanner.zzAtEOF ) scanner.next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
