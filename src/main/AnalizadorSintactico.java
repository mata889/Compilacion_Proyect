package main;

import java.io.*;
import java_cup.runtime.*;
import java.util.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class AnalizadorSintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public AnalizadorSintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public AnalizadorSintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\134\000\002\002\004\000\002\002\007\000\002\013" +
    "\006\000\002\020\013\000\002\020\004\000\002\017\020" +
    "\000\002\007\004\000\002\007\004\000\002\007\002\000" +
    "\002\030\004\000\002\030\004\000\002\030\002\000\002" +
    "\031\010\000\002\031\003\000\002\031\003\000\002\031" +
    "\004\000\002\031\003\000\002\031\006\000\002\031\003" +
    "\000\002\031\003\000\002\031\003\000\002\031\003\000" +
    "\002\031\006\000\002\032\003\000\002\032\003\000\002" +
    "\032\003\000\002\010\004\000\002\010\003\000\002\010" +
    "\003\000\002\015\010\000\002\015\003\000\002\015\003" +
    "\000\002\015\004\000\002\015\003\000\002\015\006\000" +
    "\002\015\003\000\002\015\003\000\002\015\003\000\002" +
    "\015\003\000\002\015\004\000\002\041\006\000\002\041" +
    "\003\000\002\034\007\000\002\034\007\000\002\034\007" +
    "\000\002\034\007\000\002\035\011\000\002\006\003\000" +
    "\002\006\003\000\002\006\003\000\002\006\003\000\002" +
    "\011\003\000\002\011\003\000\002\016\005\000\002\016" +
    "\005\000\002\016\004\000\002\016\003\000\002\012\013" +
    "\000\002\012\014\000\002\036\005\000\002\037\005\000" +
    "\002\037\003\000\002\037\005\000\002\040\003\000\002" +
    "\005\003\000\002\005\003\000\002\005\003\000\002\003" +
    "\012\000\002\014\010\000\002\014\006\000\002\014\002" +
    "\000\002\026\011\000\002\027\010\000\002\027\010\000" +
    "\002\027\006\000\002\027\003\000\002\004\003\000\002" +
    "\004\005\000\002\004\003\000\002\021\003\000\002\021" +
    "\004\000\002\021\005\000\002\022\003\000\002\022\005" +
    "\000\002\024\003\000\002\024\006\000\002\024\003\000" +
    "\002\024\005\000\002\024\004\000\002\023\003\000\002" +
    "\025\003\000\002\025\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\343\000\004\037\005\001\002\000\004\002\345\001" +
    "\002\000\004\040\006\001\002\000\004\016\007\001\002" +
    "\000\032\003\025\017\ufff9\025\030\026\021\027\015\032" +
    "\031\036\022\041\032\050\016\053\034\054\023\055\026" +
    "\001\002\000\034\003\025\014\ufff9\017\ufff9\025\030\026" +
    "\021\027\015\032\031\036\022\041\032\050\016\053\034" +
    "\054\023\055\026\001\002\000\034\003\uffdd\014\uffdd\017" +
    "\uffdd\025\uffdd\026\uffdd\027\uffdd\032\uffdd\036\uffdd\041\uffdd" +
    "\050\uffdd\053\uffdd\054\uffdd\055\uffdd\001\002\000\034\003" +
    "\uffdb\014\uffdb\017\uffdb\025\uffdb\026\uffdb\027\uffdb\032\uffdb" +
    "\036\uffdb\041\uffdb\050\uffdb\053\uffdb\054\uffdb\055\uffdb\001" +
    "\002\000\034\003\uffdc\014\uffdc\017\uffdc\025\uffdc\026\uffdc" +
    "\027\uffdc\032\uffdc\036\uffdc\041\uffdc\050\uffdc\053\uffdc\054" +
    "\uffdc\055\uffdc\001\002\000\034\003\uffde\014\uffde\017\uffde" +
    "\025\uffde\026\uffde\027\uffde\032\uffde\036\uffde\041\uffde\050" +
    "\uffde\053\uffde\054\uffde\055\uffde\001\002\000\004\011\323" +
    "\001\002\000\006\011\272\015\320\001\002\000\004\017" +
    "\317\001\002\000\034\003\uffe2\014\uffe2\017\uffe2\025\uffe2" +
    "\026\uffe2\027\uffe2\032\uffe2\036\uffe2\041\uffe2\050\uffe2\053" +
    "\uffe2\054\uffe2\055\uffe2\001\002\000\022\003\241\011\235" +
    "\022\232\024\043\034\040\035\042\042\242\050\237\001" +
    "\002\000\012\004\055\005\051\006\053\007\054\001\002" +
    "\000\004\011\172\001\002\000\034\003\uffe0\014\uffe0\017" +
    "\uffe0\025\uffe0\026\uffe0\027\uffe0\032\uffe0\036\uffe0\041\uffe0" +
    "\050\uffe0\053\uffe0\054\uffe0\055\uffe0\001\002\000\032\003" +
    "\025\010\uffd8\025\030\026\021\027\015\032\031\036\022" +
    "\041\032\050\016\053\034\054\023\055\026\001\002\000" +
    "\004\011\162\001\002\000\004\010\161\001\002\000\004" +
    "\011\144\001\002\000\004\011\116\001\002\000\014\004" +
    "\055\005\051\006\053\007\054\050\052\001\002\000\034" +
    "\003\uffe3\014\uffe3\017\uffe3\025\uffe3\026\uffe3\027\uffe3\032" +
    "\uffe3\036\uffe3\041\uffe3\050\uffe3\053\uffe3\054\uffe3\055\uffe3" +
    "\001\002\000\004\011\037\001\002\000\034\003\025\014" +
    "\ufff9\017\ufff9\025\030\026\021\027\015\032\031\036\022" +
    "\041\032\050\016\053\034\054\023\055\026\001\002\000" +
    "\006\014\ufffb\017\ufffb\001\002\000\012\024\043\034\040" +
    "\035\042\050\041\001\002\000\024\010\uffc1\012\uffc1\013" +
    "\uffc1\017\uffc1\020\uffc1\021\uffc1\022\uffc1\023\uffc1\040\uffc1" +
    "\001\002\000\004\012\047\001\002\000\024\010\uffbf\012" +
    "\uffbf\013\uffbf\017\uffbf\020\uffbf\021\uffbf\022\uffbf\023\uffbf" +
    "\040\uffbf\001\002\000\024\010\uffc0\012\uffc0\013\uffc0\017" +
    "\uffc0\020\uffc0\021\uffc0\022\uffc0\023\uffc0\040\uffc0\001\002" +
    "\000\004\012\045\001\002\000\004\010\046\001\002\000" +
    "\036\003\uffd6\014\uffd6\017\uffd6\025\uffd6\026\uffd6\027\uffd6" +
    "\032\uffd6\036\uffd6\041\uffd6\047\uffd6\050\uffd6\053\uffd6\054" +
    "\uffd6\055\uffd6\001\002\000\004\010\050\001\002\000\036" +
    "\003\uffd7\014\uffd7\017\uffd7\025\uffd7\026\uffd7\027\uffd7\032" +
    "\uffd7\036\uffd7\041\uffd7\047\uffd7\050\uffd7\053\uffd7\054\uffd7" +
    "\055\uffd7\001\002\000\004\050\uffd1\001\002\000\004\015" +
    "\073\001\002\000\004\050\uffd0\001\002\000\004\050\uffcf" +
    "\001\002\000\004\050\uffd2\001\002\000\004\050\057\001" +
    "\002\000\010\003\063\010\064\015\061\001\002\000\036" +
    "\003\uffff\014\uffff\017\uffff\025\uffff\026\uffff\027\uffff\032" +
    "\uffff\036\uffff\041\uffff\047\uffff\050\uffff\053\uffff\054\uffff" +
    "\055\uffff\001\002\000\012\024\043\034\040\035\042\050" +
    "\067\001\002\000\036\003\uffcd\014\uffcd\017\uffcd\025\uffcd" +
    "\026\uffcd\027\uffcd\032\uffcd\036\uffcd\041\uffcd\047\uffcd\050" +
    "\uffcd\053\uffcd\054\uffcd\055\uffcd\001\002\000\036\003\uffc9" +
    "\014\uffc9\017\uffc9\025\uffc9\026\uffc9\027\uffc9\032\uffc9\036" +
    "\uffc9\041\uffc9\047\uffc9\050\uffc9\053\uffc9\054\uffc9\055\uffc9" +
    "\001\002\000\036\003\uffce\014\uffce\017\uffce\025\uffce\026" +
    "\uffce\027\uffce\032\uffce\036\uffce\041\uffce\047\uffce\050\uffce" +
    "\053\uffce\054\uffce\055\uffce\001\002\000\004\010\066\001" +
    "\002\000\036\003\uffca\014\uffca\017\uffca\025\uffca\026\uffca" +
    "\027\uffca\032\uffca\036\uffca\041\uffca\047\uffca\050\uffca\053" +
    "\uffca\054\uffca\055\uffca\001\002\000\004\010\072\001\002" +
    "\000\004\010\071\001\002\000\036\003\uffcc\014\uffcc\017" +
    "\uffcc\025\uffcc\026\uffcc\027\uffcc\032\uffcc\036\uffcc\041\uffcc" +
    "\047\uffcc\050\uffcc\053\uffcc\054\uffcc\055\uffcc\001\002\000" +
    "\036\003\uffcb\014\uffcb\017\uffcb\025\uffcb\026\uffcb\027\uffcb" +
    "\032\uffcb\036\uffcb\041\uffcb\047\uffcb\050\uffcb\053\uffcb\054" +
    "\uffcb\055\uffcb\001\002\000\004\052\074\001\002\000\004" +
    "\051\075\001\002\000\004\011\076\001\002\000\004\034" +
    "\077\001\002\000\004\012\100\001\002\000\006\010\103" +
    "\016\101\001\002\000\012\016\101\024\043\034\040\035" +
    "\042\001\002\000\004\010\104\001\002\000\036\003\uffc8" +
    "\014\uffc8\017\uffc8\025\uffc8\026\uffc8\027\uffc8\032\uffc8\036" +
    "\uffc8\041\uffc8\047\uffc8\050\uffc8\053\uffc8\054\uffc8\055\uffc8" +
    "\001\002\000\036\003\uffc7\014\uffc7\017\uffc7\025\uffc7\026" +
    "\uffc7\027\uffc7\032\uffc7\036\uffc7\041\uffc7\047\uffc7\050\uffc7" +
    "\053\uffc7\054\uffc7\055\uffc7\001\002\000\006\017\uffc4\020" +
    "\114\001\002\000\004\020\112\001\002\000\006\017\uffc2" +
    "\020\uffc2\001\002\000\004\017\111\001\002\000\010\010" +
    "\uffc6\017\uffc6\020\uffc6\001\002\000\004\016\101\001\002" +
    "\000\004\017\uffc3\001\002\000\012\016\101\024\043\034" +
    "\040\035\042\001\002\000\004\017\uffc5\001\002\000\004" +
    "\050\117\001\002\000\004\012\120\001\002\000\004\040" +
    "\121\001\002\000\010\003\123\045\125\046\122\001\002" +
    "\000\004\013\141\001\002\000\004\033\uffb6\001\002\000" +
    "\004\033\140\001\002\000\012\024\043\034\040\035\042" +
    "\050\126\001\002\000\004\013\134\001\002\000\004\013" +
    "\130\001\002\000\032\003\025\014\ufff9\025\030\026\021" +
    "\027\015\032\031\036\022\041\032\050\016\053\034\054" +
    "\023\055\026\001\002\000\004\014\132\001\002\000\010" +
    "\003\123\045\125\046\122\001\002\000\004\033\uffb8\001" +
    "\002\000\032\003\025\014\ufff9\025\030\026\021\027\015" +
    "\032\031\036\022\041\032\050\016\053\034\054\023\055" +
    "\026\001\002\000\004\014\136\001\002\000\010\003\123" +
    "\045\125\046\122\001\002\000\004\033\uffb9\001\002\000" +
    "\036\003\uffba\014\uffba\017\uffba\025\uffba\026\uffba\027\uffba" +
    "\032\uffba\036\uffba\041\uffba\047\uffba\050\uffba\053\uffba\054" +
    "\uffba\055\uffba\001\002\000\032\003\025\014\ufff9\025\030" +
    "\026\021\027\015\032\031\036\022\041\032\050\016\053" +
    "\034\054\023\055\026\001\002\000\004\014\143\001\002" +
    "\000\004\033\uffb7\001\002\000\004\050\145\001\002\000" +
    "\004\043\146\001\002\000\004\034\147\001\002\000\004" +
    "\044\150\001\002\000\004\044\151\001\002\000\004\044" +
    "\152\001\002\000\004\034\153\001\002\000\004\012\154" +
    "\001\002\000\004\040\155\001\002\000\004\013\156\001" +
    "\002\000\032\003\025\014\ufff9\025\030\026\021\027\015" +
    "\032\031\036\022\041\032\050\016\053\034\054\023\055" +
    "\026\001\002\000\004\014\160\001\002\000\036\003\ufffc" +
    "\014\ufffc\017\ufffc\025\ufffc\026\ufffc\027\ufffc\032\ufffc\036" +
    "\ufffc\041\ufffc\047\ufffc\050\ufffc\053\ufffc\054\ufffc\055\ufffc" +
    "\001\002\000\034\003\uffe1\014\uffe1\017\uffe1\025\uffe1\026" +
    "\uffe1\027\uffe1\032\uffe1\036\uffe1\041\uffe1\050\uffe1\053\uffe1" +
    "\054\uffe1\055\uffe1\001\002\000\004\050\163\001\002\000" +
    "\004\020\164\001\002\000\004\050\165\001\002\000\004" +
    "\012\166\001\002\000\004\010\167\001\002\000\036\003" +
    "\uffd3\014\uffd3\017\uffd3\025\uffd3\026\uffd3\027\uffd3\032\uffd3" +
    "\036\uffd3\041\uffd3\047\uffd3\050\uffd3\053\uffd3\054\uffd3\055" +
    "\uffd3\001\002\000\034\003\ufffd\014\ufffd\017\ufffd\025\ufffd" +
    "\026\ufffd\027\ufffd\032\ufffd\036\ufffd\041\ufffd\050\ufffd\053" +
    "\ufffd\054\ufffd\055\ufffd\001\002\000\034\003\uffda\014\uffda" +
    "\017\uffda\025\uffda\026\uffda\027\uffda\032\uffda\036\uffda\041" +
    "\uffda\050\uffda\053\uffda\054\uffda\055\uffda\001\002\000\012" +
    "\024\043\034\040\035\042\050\173\001\002\000\004\012" +
    "\177\001\002\000\004\012\175\001\002\000\004\010\176" +
    "\001\002\000\036\003\uffd4\014\uffd4\017\uffd4\025\uffd4\026" +
    "\uffd4\027\uffd4\032\uffd4\036\uffd4\041\uffd4\047\uffd4\050\uffd4" +
    "\053\uffd4\054\uffd4\055\uffd4\001\002\000\004\010\200\001" +
    "\002\000\036\003\uffd5\014\uffd5\017\uffd5\025\uffd5\026\uffd5" +
    "\027\uffd5\032\uffd5\036\uffd5\041\uffd5\047\uffd5\050\uffd5\053" +
    "\uffd5\054\uffd5\055\uffd5\001\002\000\004\050\202\001\002" +
    "\000\004\011\203\001\002\000\016\003\205\004\055\005" +
    "\051\006\053\007\054\050\204\001\002\000\004\012\uffe6" +
    "\001\002\000\004\012\uffe5\001\002\000\004\050\310\001" +
    "\002\000\004\012\210\001\002\000\004\016\211\001\002" +
    "\000\032\003\225\017\ufff6\025\030\026\223\027\015\032" +
    "\031\041\032\047\212\050\221\053\034\054\023\055\026" +
    "\001\002\000\004\011\301\001\002\000\032\003\225\017" +
    "\ufff6\025\030\026\223\027\015\032\031\041\032\047\212" +
    "\050\221\053\034\054\023\055\026\001\002\000\032\003" +
    "\uffee\017\uffee\025\uffee\026\uffee\027\uffee\032\uffee\041\uffee" +
    "\047\uffee\050\uffee\053\uffee\054\uffee\055\uffee\001\002\000" +
    "\032\003\uffec\017\uffec\025\uffec\026\uffec\027\uffec\032\uffec" +
    "\041\uffec\047\uffec\050\uffec\053\uffec\054\uffec\055\uffec\001" +
    "\002\000\032\003\uffed\017\uffed\025\uffed\026\uffed\027\uffed" +
    "\032\uffed\041\uffed\047\uffed\050\uffed\053\uffed\054\uffed\055" +
    "\uffed\001\002\000\032\003\uffef\017\uffef\025\uffef\026\uffef" +
    "\027\uffef\032\uffef\041\uffef\047\uffef\050\uffef\053\uffef\054" +
    "\uffef\055\uffef\001\002\000\004\017\277\001\002\000\006" +
    "\011\272\015\271\001\002\000\032\003\ufff3\017\ufff3\025" +
    "\ufff3\026\ufff3\027\ufff3\032\ufff3\041\ufff3\047\ufff3\050\ufff3" +
    "\053\ufff3\054\ufff3\055\ufff3\001\002\000\022\003\241\011" +
    "\235\022\232\024\043\034\040\035\042\042\242\050\237" +
    "\001\002\000\032\003\ufff1\017\ufff1\025\ufff1\026\ufff1\027" +
    "\ufff1\032\ufff1\041\ufff1\047\ufff1\050\ufff1\053\ufff1\054\ufff1" +
    "\055\ufff1\001\002\000\034\003\225\010\uffd8\017\ufff6\025" +
    "\030\026\223\027\015\032\031\041\032\047\212\050\221" +
    "\053\034\054\023\055\026\001\002\000\004\010\230\001" +
    "\002\000\032\003\ufff4\017\ufff4\025\ufff4\026\ufff4\027\ufff4" +
    "\032\ufff4\041\ufff4\047\ufff4\050\ufff4\053\ufff4\054\ufff4\055" +
    "\ufff4\001\002\000\032\003\ufff2\017\ufff2\025\ufff2\026\ufff2" +
    "\027\ufff2\032\ufff2\041\ufff2\047\ufff2\050\ufff2\053\ufff2\054" +
    "\ufff2\055\ufff2\001\002\000\004\017\ufff7\001\002\000\016" +
    "\011\uffa8\024\uffa8\034\uffa8\035\uffa8\042\uffa8\050\uffa8\001" +
    "\002\000\022\010\uffab\012\uffab\013\uffab\020\uffab\021\uffab" +
    "\022\uffab\023\uffab\040\uffab\001\002\000\022\010\uffb2\012" +
    "\uffb2\013\uffb2\020\uffb2\021\uffb2\022\uffb2\023\250\040\uffb2" +
    "\001\002\000\022\003\241\011\235\022\232\024\043\034" +
    "\040\035\042\042\242\050\237\001\002\000\004\040\263" +
    "\001\002\000\024\010\uffad\011\255\012\uffad\013\uffad\020" +
    "\uffad\021\uffad\022\uffad\023\uffad\040\uffad\001\002\000\022" +
    "\010\uffaf\012\uffaf\013\uffaf\020\uffaf\021\uffaf\022\uffaf\023" +
    "\uffaf\040\uffaf\001\002\000\014\010\uffb3\012\uffb3\013\uffb3" +
    "\020\uffb3\040\uffb3\001\002\000\016\011\235\024\043\034" +
    "\040\035\042\042\242\050\237\001\002\000\016\011\235" +
    "\024\043\034\040\035\042\042\242\050\237\001\002\000" +
    "\020\010\uffb5\012\uffb5\013\uffb5\020\uffb5\021\245\022\246" +
    "\040\uffb5\001\002\000\022\003\241\011\235\022\232\024" +
    "\043\034\040\035\042\042\242\050\237\001\002\000\016" +
    "\011\235\024\043\034\040\035\042\042\242\050\237\001" +
    "\002\000\022\010\uffb0\012\uffb0\013\uffb0\020\uffb0\021\uffb0" +
    "\022\uffb0\023\250\040\uffb0\001\002\000\016\011\235\024" +
    "\043\034\040\035\042\042\242\050\237\001\002\000\022" +
    "\010\uffae\012\uffae\013\uffae\020\uffae\021\uffae\022\uffae\023" +
    "\uffae\040\uffae\001\002\000\014\010\uffb4\012\uffb4\013\uffb4" +
    "\020\uffb4\040\uffb4\001\002\000\022\010\uffb1\012\uffb1\013" +
    "\uffb1\020\uffb1\021\uffb1\022\uffb1\023\250\040\uffb1\001\002" +
    "\000\022\010\uffa9\012\uffa9\013\uffa9\020\uffa9\021\uffa9\022" +
    "\uffa9\023\uffa9\040\uffa9\001\002\000\022\003\241\011\235" +
    "\022\232\024\043\034\040\035\042\042\242\050\237\001" +
    "\002\000\006\012\uffa7\020\uffa7\001\002\000\006\012\261" +
    "\020\260\001\002\000\022\003\241\011\235\022\232\024" +
    "\043\034\040\035\042\042\242\050\237\001\002\000\022" +
    "\010\uffac\012\uffac\013\uffac\020\uffac\021\uffac\022\uffac\023" +
    "\uffac\040\uffac\001\002\000\006\012\uffa6\020\uffa6\001\002" +
    "\000\004\013\264\001\002\000\032\003\025\014\ufff9\025" +
    "\030\026\021\027\015\032\031\036\022\041\032\050\016" +
    "\053\034\054\023\055\026\001\002\000\004\014\266\001" +
    "\002\000\032\003\ufff5\017\ufff5\025\ufff5\026\ufff5\027\ufff5" +
    "\032\ufff5\041\ufff5\047\ufff5\050\ufff5\053\ufff5\054\ufff5\055" +
    "\ufff5\001\002\000\004\012\270\001\002\000\022\010\uffaa" +
    "\012\uffaa\013\uffaa\020\uffaa\021\uffaa\022\uffaa\023\uffaa\040" +
    "\uffaa\001\002\000\022\003\241\011\235\022\232\024\043" +
    "\034\040\035\042\042\242\050\237\001\002\000\016\003" +
    "\205\004\055\005\051\006\053\007\054\050\204\001\002" +
    "\000\004\012\274\001\002\000\006\010\uffd9\012\uffd9\001" +
    "\002\000\004\010\276\001\002\000\032\003\ufff0\017\ufff0" +
    "\025\ufff0\026\ufff0\027\ufff0\032\ufff0\041\ufff0\047\ufff0\050" +
    "\ufff0\053\ufff0\054\ufff0\055\ufff0\001\002\000\034\003\ufffe" +
    "\014\ufffe\017\ufffe\025\ufffe\026\ufffe\027\ufffe\032\ufffe\036" +
    "\ufffe\041\ufffe\050\ufffe\053\ufffe\054\ufffe\055\ufffe\001\002" +
    "\000\004\017\ufff8\001\002\000\014\003\305\024\043\034" +
    "\040\035\042\050\303\001\002\000\004\012\307\001\002" +
    "\000\006\011\272\012\uffe8\001\002\000\004\012\uffe9\001" +
    "\002\000\004\012\uffd8\001\002\000\004\012\uffea\001\002" +
    "\000\032\003\uffeb\017\uffeb\025\uffeb\026\uffeb\027\uffeb\032" +
    "\uffeb\041\uffeb\047\uffeb\050\uffeb\053\uffeb\054\uffeb\055\uffeb" +
    "\001\002\000\004\012\uffe7\001\002\000\004\040\312\001" +
    "\002\000\004\013\313\001\002\000\026\003\314\025\030" +
    "\026\021\027\015\032\031\041\032\050\016\053\034\054" +
    "\023\055\026\001\002\000\030\003\314\010\uffd8\025\030" +
    "\026\021\027\015\032\031\041\032\050\016\053\034\054" +
    "\023\055\026\001\002\000\004\014\316\001\002\000\034" +
    "\003\uffe4\014\uffe4\017\uffe4\025\uffe4\026\uffe4\027\uffe4\032" +
    "\uffe4\036\uffe4\041\uffe4\050\uffe4\053\uffe4\054\uffe4\055\uffe4" +
    "\001\002\000\004\002\000\001\002\000\022\003\241\011" +
    "\235\022\232\024\043\034\040\035\042\042\242\050\237" +
    "\001\002\000\004\010\322\001\002\000\034\003\uffdf\014" +
    "\uffdf\017\uffdf\025\uffdf\026\uffdf\027\uffdf\032\uffdf\036\uffdf" +
    "\041\uffdf\050\uffdf\053\uffdf\054\uffdf\055\uffdf\001\002\000" +
    "\022\003\241\011\235\022\232\024\043\034\040\035\042" +
    "\042\242\050\237\001\002\000\004\012\325\001\002\000" +
    "\004\013\326\001\002\000\026\003\314\025\030\026\021" +
    "\027\015\032\031\041\032\050\016\053\034\054\023\055" +
    "\026\001\002\000\004\014\330\001\002\000\042\003\uffbb" +
    "\014\uffbb\017\uffbb\025\uffbb\026\uffbb\027\uffbb\030\333\031" +
    "\332\032\uffbb\036\uffbb\041\uffbb\047\uffbb\050\uffbb\053\uffbb" +
    "\054\uffbb\055\uffbb\001\002\000\036\003\uffbe\014\uffbe\017" +
    "\uffbe\025\uffbe\026\uffbe\027\uffbe\032\uffbe\036\uffbe\041\uffbe" +
    "\047\uffbe\050\uffbe\053\uffbe\054\uffbe\055\uffbe\001\002\000" +
    "\004\013\341\001\002\000\022\003\241\011\235\022\232" +
    "\024\043\034\040\035\042\042\242\050\237\001\002\000" +
    "\004\013\335\001\002\000\026\003\314\025\030\026\021" +
    "\027\015\032\031\041\032\050\016\053\034\054\023\055" +
    "\026\001\002\000\004\014\337\001\002\000\042\003\uffbb" +
    "\014\uffbb\017\uffbb\025\uffbb\026\uffbb\027\uffbb\030\333\031" +
    "\332\032\uffbb\036\uffbb\041\uffbb\047\uffbb\050\uffbb\053\uffbb" +
    "\054\uffbb\055\uffbb\001\002\000\036\003\uffbd\014\uffbd\017" +
    "\uffbd\025\uffbd\026\uffbd\027\uffbd\032\uffbd\036\uffbd\041\uffbd" +
    "\047\uffbd\050\uffbd\053\uffbd\054\uffbd\055\uffbd\001\002\000" +
    "\026\003\314\025\030\026\021\027\015\032\031\041\032" +
    "\050\016\053\034\054\023\055\026\001\002\000\004\014" +
    "\343\001\002\000\036\003\uffbc\014\uffbc\017\uffbc\025\uffbc" +
    "\026\uffbc\027\uffbc\032\uffbc\036\uffbc\041\uffbc\047\uffbc\050" +
    "\uffbc\053\uffbc\054\uffbc\055\uffbc\001\002\000\006\014\ufffa" +
    "\017\ufffa\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\343\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\030\003\023\007\016" +
    "\012\011\013\032\015\034\017\017\020\007\026\013\034" +
    "\010\035\012\041\026\001\001\000\030\003\023\007\343" +
    "\012\011\013\032\015\034\017\017\020\007\026\013\034" +
    "\010\035\012\041\026\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\016\004\310\005\232\021\243\022\233\023\242\024" +
    "\237\001\001\000\004\006\200\001\001\000\002\001\001" +
    "\000\002\001\001\000\026\003\023\012\011\013\032\015" +
    "\170\017\017\020\167\026\013\034\010\035\012\041\026" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\006\055\001\001\000\002" +
    "\001\001\000\002\001\001\000\030\003\023\007\035\012" +
    "\011\013\032\015\034\017\017\020\007\026\013\034\010" +
    "\035\012\041\026\001\001\000\002\001\001\000\004\005" +
    "\043\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\010" +
    "\011\057\016\061\033\064\001\001\000\002\001\001\000" +
    "\004\005\067\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\036\101" +
    "\001\001\000\012\005\106\036\105\037\107\040\104\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\036\112\001\001" +
    "\000\002\001\001\000\012\005\106\036\105\037\114\040" +
    "\104\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\027\123\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\005\126\001\001\000\002\001\001\000\002\001\001\000" +
    "\030\003\023\007\130\012\011\013\032\015\034\017\017" +
    "\020\007\026\013\034\010\035\012\041\026\001\001\000" +
    "\002\001\001\000\004\027\132\001\001\000\002\001\001" +
    "\000\030\003\023\007\134\012\011\013\032\015\034\017" +
    "\017\020\007\026\013\034\010\035\012\041\026\001\001" +
    "\000\002\001\001\000\004\027\136\001\001\000\002\001" +
    "\001\000\002\001\001\000\030\003\023\007\141\012\011" +
    "\013\032\015\034\017\017\020\007\026\013\034\010\035" +
    "\012\041\026\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\030\003\023\007\156\012\011\013\032\015\034" +
    "\017\017\020\007\026\013\034\010\035\012\041\026\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\005\173\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\006\006\205\010\206\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\026\003\223\012\214" +
    "\013\226\017\221\026\216\030\217\031\212\034\213\035" +
    "\215\041\225\001\001\000\002\001\001\000\026\003\223" +
    "\012\214\013\226\017\221\026\216\030\277\031\212\034" +
    "\213\035\215\041\225\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\016\004\235" +
    "\005\232\021\243\022\233\023\242\024\237\001\001\000" +
    "\002\001\001\000\026\003\223\012\214\013\226\017\221" +
    "\026\216\030\230\031\212\034\213\035\215\041\225\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\016\004\266\005\232\021\243\022\233" +
    "\023\242\024\237\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\005\232" +
    "\024\253\001\001\000\010\005\232\022\252\024\237\001" +
    "\001\000\002\001\001\000\016\004\251\005\232\021\243" +
    "\022\233\023\242\024\237\001\001\000\010\005\232\022" +
    "\246\024\237\001\001\000\002\001\001\000\006\005\232" +
    "\024\250\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\020\004\255\005\232" +
    "\021\243\022\233\023\242\024\237\025\256\001\001\000" +
    "\002\001\001\000\002\001\001\000\016\004\261\005\232" +
    "\021\243\022\233\023\242\024\237\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\030\003\023" +
    "\007\264\012\011\013\032\015\034\017\017\020\007\026" +
    "\013\034\010\035\012\041\026\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\016\004\274\005\232\021\243\022\233\023\242\024\237" +
    "\001\001\000\006\006\205\010\272\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\005\305\032" +
    "\301\041\303\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\024\003\023\012\011\013\032\015\314\017" +
    "\017\026\013\034\010\035\012\041\026\001\001\000\024" +
    "\003\023\012\011\013\032\015\170\017\017\026\013\034" +
    "\010\035\012\041\026\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\016\004\320\005\232\021" +
    "\243\022\233\023\242\024\237\001\001\000\002\001\001" +
    "\000\002\001\001\000\016\004\323\005\232\021\243\022" +
    "\233\023\242\024\237\001\001\000\002\001\001\000\002" +
    "\001\001\000\024\003\023\012\011\013\032\015\326\017" +
    "\017\026\013\034\010\035\012\041\026\001\001\000\002" +
    "\001\001\000\004\014\330\001\001\000\002\001\001\000" +
    "\002\001\001\000\016\004\333\005\232\021\243\022\233" +
    "\023\242\024\237\001\001\000\002\001\001\000\024\003" +
    "\023\012\011\013\032\015\335\017\017\026\013\034\010" +
    "\035\012\041\026\001\001\000\002\001\001\000\004\014" +
    "\337\001\001\000\002\001\001\000\024\003\023\012\011" +
    "\013\032\015\341\017\017\026\013\034\010\035\012\041" +
    "\026\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalizadorSintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalizadorSintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalizadorSintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    public static Nodo arbol;
    public static int token=0;
    public static int parNum=0;
    
    public static int SintacticError =0;

    public String tokenes_id(int id){
        switch(id){
            case 0: return "EOF";
            case 1: return "error";
            case 2: return "VERDAD";
            case 3: return "NUMERITO";
            case 4: return "LETRA";
            case 5: return "WORD";
            case 6: return "PUNTOC";
            case 7: return "PARENTESISA";
            case 8: return "PARENTESISC";
            case 9: return "ABRIRC";
            case 10: return "CERRARC";
            case 11: return "ASSIGNMENT";
            case 12: return "BRACKETA";
            case 13: return "BRACKETC";
            case 14: return "COMMA";
            case 15: return "OPER";
            case 16: return "OPEA_SUM";
            case 17: return "OPEA_MULT";
            case 18: return "BOOL";
            case 19: return "FOR";
            case 20: return "WLE";
            case 21: return "IF";
            case 22: return "EIF";
            case 23: return "ELSE";
            case 24: return "BLOCK";
            case 25: return "END";
            case 26: return "NUM";
            case 27: return "LETTER";
            case 28: return "FUNC";
            case 29: return "START";
            case 30: return "FLECHA";
            case 31: return "VAR";
            case 32: return "NOT";
            case 33: return "IN";
            case 34: return "PUNTO";
            case 35: return "CASE";
            case 36: return "DEFAULT";
            case 37: return "REPLY";
            case 38: return "ID";
            case 39: return "ARRAY";
            case 40: return "NEW";
            case 41: return "THROW";
            case 42: return "THROWDOWN";
            case 43: return "CATCH";
            default: return "Nothing";
        }
    }

         /**
     * Método al que se llama automáticamente ante algún error sintactico.
     **/ 
    public void syntax_error(Symbol s){ 
        System.out.println("Error Sintáctico en la Columna " + (s.left) +
        " linear "+s.right+ ". No se esperaba este componente: " +s.value+".");
        
        SintacticError++;

        List expected =expected_token_ids();
        if(expected.size()!=0){
            int id;
            System.out.print("TOKEN ESPERADO..");
            for(int i=0;i<expected.size();i++){
                id = (int) expected.get(i);
                    if (i == expected.size() - 1) {
                        System.out.println(tokenes_id(id));
                    } else {
                        System.out.println(tokenes_id(id) + " | ");
                    }
            }
        }
    } 

    /**
     * Método al que se llama automáticamente ante algún error sintáctico 
     * en el que ya no es posible una recuperación de errores.
     **/ 
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{ 
        System.out.println("Error síntactico irrecuperable en la columna " + 
        (s.left)+ " linear "+s.right+". Componente " + s.value + 
        " no reconocido.");
        System.out.println("Sintaxis mal escrito,irrecuperable"); 
    }  
    


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$AnalizadorSintactico$actions {
  private final AnalizadorSintactico parser;

  /** Constructor */
  CUP$AnalizadorSintactico$actions(AnalizadorSintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico$do_action_part00000000(
    int                        CUP$AnalizadorSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico$parser,
    java.util.Stack            CUP$AnalizadorSintactico$stack,
    int                        CUP$AnalizadorSintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalizadorSintactico$result;

      /* select the action based on the action number */
      switch (CUP$AnalizadorSintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		RESULT = start_val;
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalizadorSintactico$parser.done_parsing();
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Program ::= START FLECHA BRACKETA body BRACKETC 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo start = new Nodo("Start",token);
    parser.token ++;

    start.addHijo((Nodo)b);
 
    

    parser.arbol = start;
    RESULT = start;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("Program",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // declara_simple ::= VAR type ID declara_puntoc 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int dcleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int dcright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object dc = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
        Nodo decl = new Nodo("Declaracion Simple",  parser.token);
        parser.token++;

        Nodo variable=new Nodo("Variable",parser.token);
        parser.token++;

        variable.addHijo(v.toString(),parser.token);
        decl.addHijo(variable);
        parser.token++;

        decl.addHijo((Nodo)t);

        Nodo id=new Nodo("ID",parser.token);
        parser.token++;

        id.addHijo(ide.toString(),parser.token);
        decl.addHijo(id);
        parser.token++;

        decl.addHijo((Nodo)dc);

        RESULT = decl;
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("declara_simple",9, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // funciones ::= FUNC type ID PARENTESISA parametro PARENTESISC BRACKETA bodyf BRACKETC 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).value;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-6)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-6)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-6)).value;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node= new Nodo("Funciones",parser.token);
    parser.token++;

    Nodo funciones=new Nodo("Funcion",parser.token);
    parser.token++;
    node.addHijo(funciones);

    node.addHijo((Nodo)t);

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id);
    parser.token++;

    node.addHijo((Nodo)p);


    node.addHijo((Nodo)b);


    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("funciones",14, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-8)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // funciones ::= error funciones 
            {
              Object RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo fun = new Nodo("Funcion",parser.token);
    parser.token++;

    fun.addHijo("Error",parser.token);

    if(f!=null){
        fun.addHijitos(((Nodo)f).getHijos());
    }

     RESULT = fun;
 
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("funciones",14, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // CicloFor ::= FOR PARENTESISA ID IN NUM PUNTO PUNTO PUNTO NUM PARENTESISC FLECHA ABRIRC body CERRARC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-11)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-11)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-11)).value;
		int nleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-9)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-9)).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-9)).value;
		int n1left = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).right;
		Object n1 = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
        Nodo ciclo = new Nodo("CicloFor",parser.token);
        parser.token++;

        Nodo ID=new Nodo("Id",parser.token);
        parser.token++;
        ID.addHijo(ide.toString(),parser.token);
        
        ciclo.addHijo(ID);
        parser.token++;
 
        Nodo num1=new Nodo("num",parser.token);
        parser.token++;
        num1.addHijo(n.toString(),parser.token);
        parser.token++;
        ciclo.addHijo(num1);

        Nodo num2=new Nodo("num",parser.token);
        parser.token++;
        num2.addHijo(n1.toString(),parser.token);
        parser.token++;
        ciclo.addHijo(num2);

        ciclo.addHijo((Nodo)b);

        RESULT= ciclo;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("CicloFor",13, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-13)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // body ::= proposition body 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
        Nodo bodyn = new Nodo("body",  parser.token);
        parser.token++;
        bodyn.addHijo((Nodo) p);
        
        if(b!=null){
            bodyn.addHijitos(((Nodo)b).getHijos());
        }

        RESULT = bodyn;
                

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("body",5, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // body ::= funciones body 
            {
              Object RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int fright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo funciones=new Nodo("Funciones",parser.token);
    parser.token++;

    funciones.addHijo((Nodo)f);

    if(b!=null){
        funciones.addHijitos(((Nodo)b).getHijos());
    }

    RESULT= funciones;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("body",5, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // body ::= 
            {
              Object RESULT =null;
		
    Nodo node=new Nodo("Vacio",parser.token);
    parser.token++;
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("body",5, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // bodyf ::= propositionf bodyf 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
        Nodo bodyn = new Nodo("body",  parser.token);
        parser.token++;

        bodyn.addHijo((Nodo) p);
        
        if(b!=null){
            bodyn.addHijitos(((Nodo)b).getHijos());
        }

        RESULT = bodyn;
                

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("bodyf",22, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // bodyf ::= error bodyf 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo bodyn = new Nodo("body",  parser.token);
    parser.token++;
    bodyn.addHijo("Error",parser.token);
    parser.token++;
    if(b!=null){
            bodyn.addHijitos(((Nodo)b).getHijos());
        }
    RESULT=bodyn;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("bodyf",22, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // bodyf ::= 
            {
              Object RESULT =null;
		
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("bodyf",22, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // propositionf ::= WLE expression FLECHA ABRIRC body CERRARC 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;

    Nodo whil=new Nodo("while",parser.token);
    parser.token++;
    node.addHijo(whil);

    node.addHijo((Nodo)e);

    node.addHijo((Nodo)b);

    RESULT= node;


              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // propositionf ::= declara_simple 
            {
              Object RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;
    node.addHijo((Nodo)d);
    
    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // propositionf ::= CicloFor 
            {
              Object RESULT =null;
		int cfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object cf = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node = new Nodo("CicloFor",parser.token);
    parser.token++;
    node.addHijo((Nodo)cf);

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // propositionf ::= llamadaFunc PUNTOC 
            {
              Object RESULT =null;
		int lfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int lfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object lf = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
        RESULT=lf;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // propositionf ::= ifState 
            {
              Object RESULT =null;
		int ifeleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int iferight = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ife = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT = ife;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // propositionf ::= ID ASSIGNMENT expression PUNTOC 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		

    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;

    Nodo ID = new Nodo("ID",parser.token);
    parser.token++;

    ID.addHijo(id.toString(),parser.token);
    node.addHijo(ID);
    parser.token++;
    
    Nodo assing =new Nodo("=",parser.token);
    parser.token++;
    node.addHijo(assing);
    
    node.addHijo((Nodo)e);

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // propositionf ::= block_switch 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=b;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // propositionf ::= print 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=p;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // propositionf ::= catch 
            {
              Object RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=c;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // propositionf ::= declara_array 
            {
              Object RESULT =null;
		int daleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int daright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object da = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT = da;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // propositionf ::= REPLY PARENTESISA devolverFunc PARENTESISC 
            {
              Object RESULT =null;
		int dfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int dfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object df = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    RESULT=df;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("propositionf",23, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // devolverFunc ::= valores 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=v;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("devolverFunc",24, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // devolverFunc ::= llamadaFunc 
            {
              Object RESULT =null;
		int lfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int lfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object lf = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=lf;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("devolverFunc",24, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // devolverFunc ::= ID 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo ID = new Nodo("ID",parser.token);
    parser.token++;

    ID.addHijo(ide.toString(),parser.token);
    
    parser.token++;

    RESULT = ID;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("devolverFunc",24, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // parametro ::= type ID 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Parametro",parser.token);
    parser.token++;

    node.addHijo((Nodo)t);

    Nodo ID = new Nodo("ID",parser.token);
    parser.token++;

    ID.addHijo(id.toString(),parser.token);
    node.addHijo(ID);
    parser.token++;

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("parametro",6, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // parametro ::= ID 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Parametro",parser.token);
    parser.token++;

    Nodo ID = new Nodo("ID",parser.token);
    parser.token++;

    ID.addHijo(id.toString(),parser.token);
    node.addHijo(ID);
    parser.token++;

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("parametro",6, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // parametro ::= error 
            {
              Object RESULT =null;
		
    Nodo error=new Nodo("Erros-parametro",parser.token);
    parser.token++;

    RESULT=error;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("parametro",6, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // proposition ::= WLE expression FLECHA ABRIRC proposition CERRARC 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;

    Nodo whil=new Nodo("while",parser.token);
    parser.token++;
    node.addHijo(whil);

    node.addHijo((Nodo)e);

    node.addHijo((Nodo)p);

    RESULT= node;


              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // proposition ::= declara_simple 
            {
              Object RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;
    node.addHijo((Nodo)d);
    
    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // proposition ::= CicloFor 
            {
              Object RESULT =null;
		int cfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object cf = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node = new Nodo("CicloFor",parser.token);
    parser.token++;
    node.addHijo((Nodo)cf);

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // proposition ::= llamadaFunc PUNTOC 
            {
              Object RESULT =null;
		int lfleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int lfright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object lf = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
        RESULT=lf;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // proposition ::= ifState 
            {
              Object RESULT =null;
		int ifeleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int iferight = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ife = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    RESULT = ife;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // proposition ::= ID ASSIGNMENT expression PUNTOC 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		

    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;

    Nodo ID = new Nodo("ID",parser.token);
    parser.token++;

    ID.addHijo(id.toString(),parser.token);
    node.addHijo(ID);
    parser.token++;

    node.addHijo((Nodo)e);


    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // proposition ::= block_switch 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=b;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // proposition ::= print 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=p;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // proposition ::= catch 
            {
              Object RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=c;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // proposition ::= declara_array 
            {
              Object RESULT =null;
		int daleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int daright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object da = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT = da;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // proposition ::= error proposition 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node = new Nodo("Proposicion",parser.token);
    parser.token++;

    node.addHijo("Error",parser.token);

    if(p!=null){
        node.addHijitos(((Nodo)p).getHijos());
    }

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("proposition",11, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // llamadaFunc ::= ID PARENTESISA parametro PARENTESISC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("Llamada de funciones",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;

    node.addHijo((Nodo)p);

    RESULT = node;


              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("llamadaFunc",31, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // llamadaFunc ::= error 
            {
              Object RESULT =null;
		
    Nodo error=new Nodo("Erros-callFunc",parser.token);
    parser.token++;

    RESULT=error;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("llamadaFunc",31, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // print ::= THROW PARENTESISA ID PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		
    Nodo node=new Nodo("Print",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID-print",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("print",26, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43: // print ::= THROW PARENTESISA valores PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		
    Nodo node=new Nodo("Print",parser.token);
    parser.token++;

    Nodo thro=new Nodo("Throw",parser.token);
    parser.token++;
    node.addHijo(thro);

    node.addHijo((Nodo)v);
 
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("print",26, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44: // print ::= THROWDOWN PARENTESISA ID PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		
    Nodo node=new Nodo("Print",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID-print",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;
 
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("print",26, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45: // print ::= THROWDOWN PARENTESISA valores PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		
    Nodo node=new Nodo("Print",parser.token);
    parser.token++;

    node.addHijo((Nodo)v);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("print",26, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46: // catch ::= CATCH PARENTESISA ID COMMA ID PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int ide2left = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int ide2right = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object ide2 = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		
    Nodo node=new Nodo("Empieza el catch",parser.token);
    parser.token++;

    Nodo catche=new Nodo("Catch",parser.token);
    parser.token++;
    node.addHijo(catche);

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;
 
    Nodo id_2=new Nodo("ID",parser.token);
    parser.token++;

    id_2.addHijo(ide2.toString(),parser.token);
    node.addHijo(id_2); 
    parser.token++;
 
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("catch",27, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-6)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47: // type ::= VERDAD 
            {
              Object RESULT =null;
		
    
    Nodo node = new Nodo("Boolena",  parser.token);
    parser.token++;
    RESULT = node;
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 48: // type ::= NUMERITO 
            {
              Object RESULT =null;
		
    Nodo node = new Nodo("NUM",  parser.token);
    parser.token++;
    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 49: // type ::= LETRA 
            {
              Object RESULT =null;
		
    Nodo node = new Nodo("Caracter",  parser.token);
    parser.token++;
    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 50: // type ::= WORD 
            {
              Object RESULT =null;
		
    Nodo node = new Nodo("String",  parser.token);
    parser.token++;
    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 51: // declara_puntoc ::= PUNTOC 
            {
              Object RESULT =null;
		
    Nodo node =new Nodo(";",parser.token);
    parser.token++;

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("declara_puntoc",7, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 52: // declara_puntoc ::= asigna 
            {
              Object RESULT =null;
		int asleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int asright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object as = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    RESULT = as;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("declara_puntoc",7, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 53: // asigna ::= ASSIGNMENT valores PUNTOC 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("asignacion",parser.token);
    parser.token++;
  
    node.addHijo((Nodo)v);
 
    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("asigna",12, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 54: // asigna ::= ASSIGNMENT ID PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("asignacion",parser.token);
    parser.token++;
 
    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;
 
    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("asigna",12, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 55: // asigna ::= vacio PUNTOC 
            {
              Object RESULT =null;
		
    Nodo node=new Nodo("asignacion",parser.token);
    parser.token++;
  
    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("asigna",12, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 56: // asigna ::= error 
            {
              Object RESULT =null;
		
    Nodo error=new Nodo("Error-asignacion",parser.token);
    parser.token++;

    RESULT=error;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("asigna",12, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 57: // declara_array ::= VAR ID ASSIGNMENT NEW ARRAY PARENTESISA NUM PARENTESISC PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)).value;
		int n1left = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object n1 = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		

    Nodo node = new Nodo("Declara Array",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;
    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;
 
    Nodo num2=new Nodo("num",parser.token);
    parser.token++;
    num2.addHijo(n1.toString(),parser.token);
    parser.token++;
    node.addHijo(num2);


    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("declara_array",8, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-8)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 58: // declara_array ::= VAR ID ASSIGNMENT NEW ARRAY PARENTESISA NUM PARENTESISC bracket_valores PUNTOC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-8)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-8)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-8)).value;
		int n1left = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object n1 = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int bvleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bvright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object bv = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node = new Nodo("Declara Array",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;
    id.addHijo(ide.toString(),parser.token);
    node.addHijo(id); 
    parser.token++;

    Nodo num2=new Nodo("num",parser.token);
    parser.token++;
    num2.addHijo(n1.toString(),parser.token);
    parser.token++;
    node.addHijo(num2);

    
 
    node.addHijo((Nodo)bv);

    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("declara_array",8, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-9)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 59: // bracket_valores ::= BRACKETA list_valores BRACKETC 
            {
              Object RESULT =null;
		int lvleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int lvright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object lv = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    RESULT=lv;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("bracket_valores",28, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 60: // list_valores ::= arrayData COMMA list_valores 
            {
              Object RESULT =null;
		int adleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int adright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object ad = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int lvleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int lvright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object lv = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Valores",parser.token);
    parser.token++;
    node.addHijo((Nodo)ad);

    if(lv!=null){
        node.addHijitos(((Nodo)lv).getHijos());
    }
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("list_valores",29, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 61: // list_valores ::= arrayData 
            {
              Object RESULT =null;
		int adleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int adright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ad = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Valores",parser.token);
    parser.token++;
    node.addHijo((Nodo)ad);
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("list_valores",29, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 62: // list_valores ::= bracket_valores COMMA bracket_valores 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int b1left = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int b1right = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b1 = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("bracket-segunda dimension",parser.token);
    parser.token++;

    node.addHijo((Nodo)b);
    node.addHijo((Nodo)b1);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("list_valores",29, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 63: // arrayData ::= valores 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=v;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("arrayData",30, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 64: // valores ::= NUM 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Valores-num",parser.token);
    parser.token++;
    node.addHijo(n.toString(),parser.token);
    parser.token++;

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("valores",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 65: // valores ::= BOOL 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Valores-boolean",parser.token);
    parser.token++;
    node.addHijo(b.toString(),parser.token);
    parser.token++;

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("valores",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 66: // valores ::= LETTER 
            {
              Object RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object l = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Valores-caracter",parser.token);
    parser.token++;
    node.addHijo(l.toString(),parser.token);
    parser.token++;

    RESULT = node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("valores",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 67: // ifState ::= IF PARENTESISA expression PARENTESISC ABRIRC proposition CERRARC eif 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)).value;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int eileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eiright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ei = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Empiezo IF",parser.token);
    parser.token++;

    Nodo ife=new Nodo("IF",parser.token);
    parser.token++;
    node.addHijo(ife);

    node.addHijo((Nodo)e);

    node.addHijo((Nodo)p);

    node.addHijo((Nodo)ei);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("ifState",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-7)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 68: // eif ::= EIF expression ABRIRC proposition CERRARC eif 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int eifeleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eiferight = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object eife = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Else if",parser.token);
    parser.token++;

    Nodo ei=new Nodo("eif",parser.token);
    parser.token++;
    node.addHijo(ei);

    node.addHijo((Nodo)e);

    node.addHijo((Nodo)p);


    if(eife!=null){
        node.addHijitos(((Nodo)eife).getHijos());
    }

    RESULT= node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("eif",10, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 69: // eif ::= ELSE ABRIRC proposition CERRARC 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object p = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("Else if",parser.token);
    parser.token++;

    Nodo ei=new Nodo("else",parser.token);
    parser.token++;
    node.addHijo(ei);

    node.addHijo((Nodo)p);
 
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("eif",10, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 70: // eif ::= 
            {
              Object RESULT =null;
		
    Nodo node = new Nodo("Vacio",parser.token);
    parser.token++;
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("eif",10, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 71: // block_switch ::= BLOCK PARENTESISA ID PARENTESISC FLECHA cases END 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
   
    Nodo node=new Nodo("Bloque Switch",parser.token);
    parser.token++;

    Nodo block=new Nodo("Block",parser.token);
    parser.token++;
    node.addHijo(block);
 
    Nodo id=new Nodo("Id",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    parser.token++;
    node.addHijo(id);
   
    if(c!=null){
        node.addHijitos(((Nodo)c).getHijos());
    }

    Nodo end=new Nodo("end",parser.token);
    parser.token++;
    node.addHijo(end);

    RESULT = node;
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("block_switch",20, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-6)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 72: // cases ::= CASE ID ABRIRC body CERRARC cases 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    Nodo node=new Nodo("Cases",parser.token);
    parser.token++;

    Nodo cas=new Nodo("Case",parser.token);
    parser.token++;
    node.addHijo(cas);

    Nodo id=new Nodo("Id",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    parser.token++;
    node.addHijo(id);
 
    node.addHijo((Nodo)b);
 
    if(c!=null){
        node.addHijitos(((Nodo)c).getHijos());
    }
    
    RESULT = node;
    


              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("cases",21, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 73: // cases ::= CASE valores ABRIRC body CERRARC cases 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    Nodo node=new Nodo("Cases",parser.token);
    parser.token++;

    Nodo cas=new Nodo("Case",parser.token);
    parser.token++;
    node.addHijo(cas);

    node.addHijo((Nodo)v);
 
    node.addHijo((Nodo)b);
  
    if(c!=null){
        node.addHijitos(((Nodo)c).getHijos());
    }

    RESULT= node;
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("cases",21, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-5)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 74: // cases ::= DEFAULT ABRIRC body CERRARC 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    
    Nodo node=new Nodo("Cases",parser.token);
    parser.token++;
 
    node.addHijo((Nodo)b);
 
    RESULT=node;
    

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("cases",21, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 75: // cases ::= error 
            {
              Object RESULT =null;
		
    Nodo error=new Nodo("Erros-case",parser.token);
    parser.token++;

    RESULT=error;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("cases",21, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 76: // expression ::= expression_simple 
            {
              Object RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int esright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object es = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    RESULT=es;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 77: // expression ::= expression_simple OPER expression 
            {
              Object RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int esright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object es = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int opright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object op = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int exleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int exright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ex = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node =new Nodo("expression simple",parser.token);
    parser.token++;
    node.addHijo((Nodo)es);

    Nodo oper=new Nodo("Operador Relacional",parser.token);
    parser.token++;

    oper.addHijo(op.toString(),parser.token);
    parser.token++;

    node.addHijo(oper);

    if(ex!=null){
        node.addHijitos(((Nodo)ex).getHijos());
    }
    
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 78: // expression ::= error 
            {
              Object RESULT =null;
		
    Nodo error=new Nodo("Error-expression",parser.token);
    parser.token++;

    RESULT=error;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 79: // expression_simple ::= termino 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    RESULT=t;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression_simple",15, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 80: // expression_simple ::= signo termino 
            {
              Object RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Expresion simple-signo",parser.token);
    parser.token++;

    node.addHijo((Nodo)s);
    node.addHijo((Nodo)t);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression_simple",15, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 81: // expression_simple ::= expression_simple OPEA_SUM termino 
            {
              Object RESULT =null;
		int exsleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int exsright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object exs = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int opright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object op = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Expresion simple",parser.token);
    parser.token++;

    if(exs!=null){
        node.addHijitos(((Nodo)exs).getHijos());
    }

    Nodo opea_sum=new Nodo("Operacion de Suma/Resta",parser.token);
    parser.token++;
    opea_sum.addHijo(op.toString(),parser.token);
    parser.token++;
    node.addHijo(opea_sum);

    node.addHijo((Nodo)t);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expression_simple",15, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 82: // termino ::= factor 
            {
              Object RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    RESULT=f;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("termino",16, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 83: // termino ::= termino OPEA_MULT factor 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int opright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object op = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int fleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node =new Nodo("Termino",parser.token);
    parser.token++;

    if(t!=null){
        node.addHijitos(((Nodo)t).getHijos());
    }
    Nodo opea_mult=new Nodo("Operador de Multiplicacion/Division",parser.token);
    parser.token++;

    opea_mult.addHijo(op.toString(),parser.token++);
    parser.token++;

    node.addHijo(opea_mult);

    node.addHijo((Nodo)f);

    RESULT=node;


              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("termino",16, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 84: // factor ::= ID 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("factor",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    parser.token++;
    node.addHijo(id);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("factor",18, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 85: // factor ::= ID PARENTESISA list_expression PARENTESISC 
            {
              Object RESULT =null;
		int ideleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).left;
		int ideright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).right;
		Object ide = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)).value;
		int listleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int listright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object list = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("factor",parser.token);
    parser.token++;

    Nodo id=new Nodo("ID",parser.token);
    parser.token++;

    id.addHijo(ide.toString(),parser.token);
    parser.token++;
    node.addHijo(id);
    

    node.addHijo((Nodo)list);
   
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("factor",18, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 86: // factor ::= valores 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    
    RESULT=v;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("factor",18, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 87: // factor ::= PARENTESISA expression PARENTESISC 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		
    Nodo node=new Nodo("factor",parser.token);
    parser.token++;
    
    node.addHijo((Nodo)e);
   
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("factor",18, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 88: // factor ::= NOT factor 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int fleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo(" factor",parser.token);
    parser.token++;

    Nodo not=new Nodo("Not",parser.token);
    parser.token++;
    not.addHijo(n.toString(),parser.token);

    if(f!=null){
        node.addHijitos(((Nodo)f).getHijos());
    }

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("factor",18, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 89: // signo ::= OPEA_SUM 
            {
              Object RESULT =null;
		int opleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int opright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object op = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node= new Nodo("Signo",parser.token);
    parser.token++;

    Nodo opea_sum=new Nodo("Operacion Suma/Resta",parser.token);
    parser.token++;
    opea_sum.addHijo(op.toString(),parser.token);
    parser.token++;

    node.addHijo(opea_sum);

    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("signo",17, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 90: // list_expression ::= expression 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Lista de Expresiones",parser.token);
    parser.token++;
    node.addHijo((Nodo)e);

    RESULT=e;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("list_expression",19, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 91: // list_expression ::= list_expression COMMA expression 
            {
              Object RESULT =null;
		int listleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int listright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object list = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    Nodo node=new Nodo("Lista de Expresiones",parser.token);
    parser.token++;

    if(list!=null){
        node.addHijitos(((Nodo)list).getHijos());
    }
    Nodo coma=new Nodo(",",parser.token);
    parser.token++;
    node.addHijo(coma);

    node.addHijo((Nodo)e);
    
    RESULT=node;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("list_expression",19, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$AnalizadorSintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico$do_action(
    int                        CUP$AnalizadorSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico$parser,
    java.util.Stack            CUP$AnalizadorSintactico$stack,
    int                        CUP$AnalizadorSintactico$top)
    throws java.lang.Exception
    {
              return CUP$AnalizadorSintactico$do_action_part00000000(
                               CUP$AnalizadorSintactico$act_num,
                               CUP$AnalizadorSintactico$parser,
                               CUP$AnalizadorSintactico$stack,
                               CUP$AnalizadorSintactico$top);
    }
}

}
