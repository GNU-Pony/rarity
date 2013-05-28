/**
 * rarity – An extensible tiling window manager
 * 
 * Copyright © 2013  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rarity;


/**
 * XKeyboard class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class XKeyboard
{
    /**
     * Shift XOR alphabetic caps lock modifer, modifier level 1
     */
    public static final int MODIFIER_SHIFT = 1;
    
    /**
     * Alernative graph (also known as right alternative) modifier, modifier level 2
     */
    public static final int MODIFIER_ALTERNATIVE_GRAPH = 2;
    
    /**
     * Modifier level 4
     */
    public static final int MODIFIER_MOD_4 = 4;
    
    
    
    /**
     * Void symbol
     */
    public static final int VOID_SYMBOL = 0xffffff;
    
    
    /**
     * Back space, back char
     */
    public static final int BACK_SPACE = 0xff08;
    
    /**
     * Tab
     */
    public static final int TAB = 0xff09;
    
    /**
     * Line feed
     */
    public static final int LINE_FEED = 0xff0a;
    
    /**
     * Clear
     */
    public static final int CLEAR = 0xff0b;
    
    /**
     * Return, enter
     */
    public static final int RETURN = 0xff0d;
    
    /**
     * Pause, break, hold
     */
    public static final int PAUSE = 0xff13;
    
    /**
     * Scroll lock
     */
    public static final int SCROLL_LOCK = 0xff14;
    
    /**
     * System request, print screen
     */
    public static final int SYSTEM_REQUEST = 0xff15;
    
    /**
     * Escape
     */
    public static final int ESCAPE = 0xff1b;
    
    /**
     * Delete, rubout
     */
    public static final int DELETE = 0xffff;
    
    
    /* International & multi-key character composition */
    
    /**
     * Multi-key character compose
     */
    public static final int MULTI_KEY = 0xff20;
    public static final int CODEINPUT = 0xff37;
    public static final int SINGLE_CANDIDATE = 0xff3c;
    public static final int MULTIPLE_CANDIDATE = 0xff3d;
    public static final int PREVIOUS_CANDIDATE = 0xff3e;
    
    
    /* Japanese keyboard support */
    
    /**
     * Kanji, Kanji convert
     */
    public static final int KANJI = 0xff21;
    
    /**
     * Cancel Conversion
     */
    public static final int MUHENKAN = 0xff22;
    
    /**
     * Start/Stop Conversion
     */
    public static final int HENKAN = 0xff23;
    
    /**
     * To Romaji
     */
    public static final int ROMAJI = 0xff24;
    
    /**
     * To Hiragana
     */
    public static final int HIRAGANA = 0xff25;
    
    /**
     * To Katakana
     */
    public static final int KATAKANA = 0xff26;
    
    /**
     * Hiragana/Katakana toggle
     */
    public static final int HIRAGANA_KATAKANA = 0xff27;
    
    /**
     * To Zenkaku
     */
    public static final int ZENKAKU = 0xff28;
    
    /**
     * To Hankaku
     */
    public static final int HANKAKU = 0xff29;
    
    /**
     * Zenkaku/Hankaku toggle
     */
    public static final int ZENKAKU_HANKAKU = 0xff2a;
    
    /**
     * Add to Dictionary
     */
    public static final int TOUROKU = 0xff2b;
    
    /**
     * Delete from Dictionary
     */
    public static final int MASSYO = 0xff2c;
    
    /**
     * Kana Lock
     */
    public static final int KANA_LOCK = 0xff2d;
    
    /**
     * Kana Shift
     */
    public static final int KANA_SHIFT = 0xff2e;
    
    /**
     * Alphanumeric Shift
     */
    public static final int EISU_SHIFT = 0xff2f;
    
    /**
     * Alphanumeric toggle
     */
    public static final int EISU_TOGGLE = 0xff30;
    
    /**
     * Codeinput
     */
    public static final int KANJI_BANGOU = 0xff37;
    
    /**
     * Multiple/All Candidate(s)
     */
    public static final int ZEN_KOHO = 0xff3d;
    
    /**
     * Previous Candidate
     */
    public static final int MAE_KOHO = 0xff3e;
    
    
    /* Cursor control & motion */
    
    public static final int HOME = 0xff50;
    public static final int LEFT = 0xff51;
    public static final int UP = 0xff52;
    public static final int RIGHT = 0xff53;
    public static final int DOWN = 0xff54;
    public static final int PAGE_UP = 0xff55;
    public static final int PAGE_DOWN = 0xff56;
    public static final int END = 0xff57;
    public static final int BEGIN = 0xff58;
    
    
    /* Misc. functions */
    
    public static final int SELECT = 0xff60;
    public static final int PRINT = 0xff61;
    public static final int EXECUTE = 0xff62;
    public static final int INSERT = 0xff63;
    public static final int UNDO = 0xff65;
    public static final int REDO = 0xff66;
    public static final int MENU = 0xff67;
    public static final int FIND = 0xff68;
    public static final int CANCEL = 0xff69;
    public static final int HELP = 0xff6a;
    public static final int BREAK = 0xff6b;
    public static final int MODE_SWTICH = 0xff7e;
    public static final int NUM_LCOK = 0xff7f;
    
    
    
    /* Keypad functions */
    
    public static final int KP_SPACE = 0xff80;
    public static final int KP_TAB = 0xff89;
    public static final int KP_ENTER = 0xff8d;
    public static final int KP_F1 = 0xff91;
    public static final int KP_F2 = 0xff92;
    public static final int KP_F3 = 0xff93;
    public static final int KP_F4 = 0xff94;
    public static final int KP_HOME = 0xff95;
    public static final int KP_LEFT = 0xff96;
    public static final int KP_UP = 0xff97;
    public static final int KP_RIGHT = 0xff98;
    public static final int KP_DOWN = 0xff99;
    public static final int KP_PAGE_UP = 0xff9a;
    public static final int KP_PAGE_DOWN = 0xff9b;
    public static final int KP_END = 0xff9c;
    public static final int KP_BEGIN = 0xff9d;
    public static final int KP_INSERT = 0xff9e;
    public static final int KP_DELETE = 0xff9f;
    public static final int KP_EQUAL = 0xffbd;
    public static final int KP_MULTIPLY = 0xffaa;
    public static final int KP_ADD = 0xffab;
    public static final int KP_SEPARATOR = 0xffac;
    public static final int KP_SUBTRACT = 0xffad;
    public static final int KP_DECIMAL = 0xffae;
    public static final int KP_DIVIDE = 0xffaf;
    
    
    /* Keypad numbers  */
    
    public static final int KP_0 = 0xffb0;
    public static final int KP_1 = 0xffb1;
    public static final int KP_2 = 0xffb2;
    public static final int KP_3 = 0xffb3;
    public static final int KP_4 = 0xffb4;
    public static final int KP_5 = 0xffb5;
    public static final int KP_6 = 0xffb6;
    public static final int KP_7 = 0xffb7;
    public static final int KP_8 = 0xffb8;
    public static final int KP_9 = 0xffb9;
    
    
    /* Auxiliary functions */
    
    public static final int F1 = 0xffbe;
    public static final int F2 = 0xffbf;
    public static final int F3 = 0xffc0;
    public static final int F4 = 0xffc1;
    public static final int F5 = 0xffc2;
    public static final int F6 = 0xffc3;
    public static final int F7 = 0xffc4;
    public static final int F8 = 0xffc5;
    public static final int F9 = 0xffc6;
    public static final int F10 = 0xffc7;
    public static final int F11 = 0xffc8;
    public static final int F12 = 0xffc9;
    public static final int F13 = 0xffca;
    public static final int F14 = 0xffcb;
    public static final int F15 = 0xffcc;
    public static final int F16 = 0xffcd;
    public static final int F17 = 0xffce;
    public static final int F18 = 0xffcf;
    public static final int F19 = 0xffd0;
    public static final int F20 = 0xffd1;
    public static final int F21 = 0xffd2;
    public static final int F22 = 0xffd3;
    public static final int F23 = 0xffd4;
    public static final int F24 = 0xffd5;
    public static final int F25 = 0xffd6;
    public static final int F26 = 0xffd7;
    public static final int F27 = 0xffd8;
    public static final int F28 = 0xffd9;
    public static final int F29 = 0xffda;
    public static final int F30 = 0xffdb;
    public static final int F31 = 0xffdc;
    public static final int F32 = 0xffdd;
    public static final int F33 = 0xffde;
    public static final int F34 = 0xffdf;
    public static final int F35 = 0xffe0;
    
    
    /* Modifiers */
    
    public static final int SHIFT_L = 0xffe1;
    public static final int SHIFT_R = 0xffe2;
    public static final int CONTROL_L = 0xffe3;
    public static final int CONTROL_R = 0xffe4;
    public static final int CAPS_LOCK = 0xffe5;
    public static final int SHIFT_LOCK = 0xffe6;
    public static final int META_L = 0xffe7;
    public static final int META_R = 0xffe8;
    public static final int ALTERNATIVE = 0xffe9;
    public static final int ALTERNATIVE_GRAPH = 0xffea;
    public static final int SUPER_L = 0xffeb;
    public static final int SUPER_R = 0xffec;
    public static final int HYPER_L = 0xffed;
    public static final int HYPER_R = 0xffee;
    
    
    /* Braille */
    
    public static final int BRAILLE_DOT_1 = 0xfff1;
    public static final int BRAILLE_DOT_2 = 0xfff2;
    public static final int BRAILLE_DOT_3 = 0xfff3;
    public static final int BRAILLE_DOT_4 = 0xfff4;
    public static final int BRAILLE_DOT_5 = 0xfff5;
    public static final int BRAILLE_DOT_6 = 0xfff6;
    public static final int BRAILLE_DOT_7 = 0xfff7;
    public static final int BRAILLE_DOT_8 = 0xfff8;
    public static final int BRAILLE_DOT_9 = 0xfff9;
    public static final int BRAILLE_DOT_10 = 0xfffa;
    
    
    public static final int ISO_LOCK           = 0xfe01;
    public static final int ISO_LEVEL_2_LATCH  = 0xfe02;
    public static final int ISO_LEVEL_3_SHIFT  = 0xfe03;
    public static final int ISO_LEVEL_3_LATCH  = 0xfe04;
    public static final int ISO_LEVEL_3_LOCK   = 0xfe05;
    public static final int ISO_LEVEL_5_SHIFT  = 0xfe11;
    public static final int ISO_LEVEL_5_LATCH  = 0xfe12;
    public static final int ISO_LEVEL_5_LOCK   = 0xfe13;
    
    /**
     * Alias for {@link MODE_SWITCH}
     */
    public static final int ISO_GROUP_SHIFT      = 0xfe7e;
    public static final int ISO_GROUP_LATCH      = 0xfe06;
    public static final int ISO_GROUP_LOCK       = 0xfe07;
    public static final int ISO_NEXT_GROUP       = 0xfe08;
    public static final int ISO_NEXT_GROUP_LOCK  = 0xfe09;
    public static final int ISO_PREV_GROUP       = 0xfe0a;
    public static final int ISO_PREV_GROUP_LOCK  = 0xfe0b;
    public static final int ISO_FIRST_GROUP      = 0xfe0c;
    public static final int ISO_FIRST_GROUP_LOCK = 0xfe0d;
    public static final int ISO_LAST_GROUP       = 0xfe0e;
    public static final int ISO_LAST_GROUP_LOCK  = 0xfe0f;
    
    public static final int ISO_LEFT_TAB                = 0xfe20;
    public static final int ISO_MOVE_LINE_UP            = 0xfe21;
    public static final int ISO_MOVE_LINE_DOWN          = 0xfe22;
    public static final int ISO_PARTIAL_LINE_UP         = 0xfe23;
    public static final int ISO_PARTIAL_LINE_DOWN       = 0xfe24;
    public static final int ISO_PARTIAL_SPACE_LEFT      = 0xfe25;
    public static final int ISO_PARTIAL_SPACE_RIGHT     = 0xfe26;
    public static final int ISO_SET_MARGIN_LEFT         = 0xfe27;
    public static final int ISO_SET_MARGIN_RIGHT        = 0xfe28;
    public static final int ISO_RELEASE_MARGIN_LEFT     = 0xfe29;
    public static final int ISO_RELEASE_MARGIN_RIGHT    = 0xfe2a;
    public static final int ISO_RELEASE_BOTH_MARGINS    = 0xfe2b;
    public static final int ISO_FAST_CURSOR_LEFT        = 0xfe2c;
    public static final int ISO_FAST_CURSOR_RIGHT       = 0xfe2d;
    public static final int ISO_FAST_CURSOR_UP          = 0xfe2e;
    public static final int ISO_FAST_CURSOR_DOWN        = 0xfe2f;
    public static final int ISO_CONTINUOUS_UNDERLINE    = 0xfe30;
    public static final int ISO_DISCONTINUOUS_UNDERLINE = 0xfe31;
    public static final int ISO_EMPHASIZE               = 0xfe32;
    public static final int ISO_CENTER_OBJECT           = 0xfe33;
    public static final int ISO_ENTER                   = 0xfe34;
    
    public static final int DEAD_GRAVE              = 0xfe50;
    public static final int DEAD_ACUTE              = 0xfe51;
    public static final int DEAD_CIRCUMFLEX         = 0xfe52;
    public static final int DEAD_TILDE              = 0xfe53;
    public static final int DEAD_MACRON             = 0xfe54;
    public static final int DEAD_BREVE              = 0xfe55;
    public static final int DEAD_ABOVEDOT           = 0xfe56;
    public static final int DEAD_DIAERESIS          = 0xfe57;
    public static final int DEAD_ABOVERING          = 0xfe58;
    public static final int DEAD_DOUBLEACUTE        = 0xfe59;
    public static final int DEAD_CARON              = 0xfe5a;
    public static final int DEAD_CEDILLA            = 0xfe5b;
    public static final int DEAD_OGONEK             = 0xfe5c;
    public static final int DEAD_IOTA               = 0xfe5d;
    public static final int DEAD_VOICED_SOUND       = 0xfe5e;
    public static final int DEAD_SEMIVOICED_SOUND   = 0xfe5f;
    public static final int DEAD_BELOWDOT           = 0xfe60;
    public static final int DEAD_HOOK               = 0xfe61;
    public static final int DEAD_HORN               = 0xfe62;
    public static final int DEAD_STROKE             = 0xfe63;
    public static final int DEAD_ABOVECOMMA         = 0xfe64;
    public static final int DEAD_ABOVEREVERSEDCOMMA = 0xfe65;
    public static final int DEAD_DOUBLEGRAVE        = 0xfe66;
    public static final int DEAD_BELOWRING          = 0xfe67;
    public static final int DEAD_BELOWMACRON        = 0xfe68;
    public static final int DEAD_BELOWCIRCUMFLEX    = 0xfe69;
    public static final int DEAD_BELOWTILDE         = 0xfe6a;
    public static final int DEAD_BELOWBREVE         = 0xfe6b;
    public static final int DEAD_BELOWDIAERESIS     = 0xfe6c;
    public static final int DEAD_INVERTEDBREVE      = 0xfe6d;
    public static final int DEAD_BELOWCOMMA         = 0xfe6e;
    public static final int DEAD_CURRENCY           = 0xfe6f;
    
    
    /* Dead vowels for universal syllable entry */
    
    public static final int DEAD_SMALL_A       = 0xfe80;
    public static final int DEAD_CAPITAL_A     = 0xfe81;
    public static final int DEAD_SMALL_E       = 0xfe82;
    public static final int DEAD_CAPITAL_E     = 0xfe83;
    public static final int DEAD_SMALL_I       = 0xfe84;
    public static final int DEAD_CAPITAL_I     = 0xfe85;
    public static final int DEAD_SMALL_O       = 0xfe86;
    public static final int DEAD_CAPITAL_O     = 0xfe87;
    public static final int DEAD_SMALL_U       = 0xfe88;
    public static final int DEAD_CAPITAL_U     = 0xfe89;
    public static final int DEAD_SMALL_SCHWA   = 0xfe8a;
    public static final int DEAD_CAPITAL_SCHWA = 0xfe8b;
    
    public static final int DEAD_GREEK = 0xfe8c;
    
    public static final int FIRST_VIRTUAL_SCREEN = 0xfed0;
    public static final int PREV_VIRTUAL_SCREEN  = 0xfed1;
    public static final int NEXT_VIRTUAL_SCREEN  = 0xfed2;
    public static final int LAST_VIRTUAL_SCREEN  = 0xfed4;
    public static final int TERMINATE_SERVER     = 0xfed5;
    
    public static final int ACCESS_X_ENABLE          = 0xfe70;
    public static final int ACCESS_X_FEEDBACK_ENABLE = 0xfe71;
    public static final int REPEAT_KEYS_ENABLE       = 0xfe72;
    public static final int SLOW_KEYS_ENABLE         = 0xfe73;
    public static final int BOUNCE_KEYS_ENABLE       = 0xfe74;
    public static final int STICKY_KEYS_ENABLE       = 0xfe75;
    public static final int MOUSE_KEYS_ENABLE        = 0xfe76;
    public static final int MOUSE_KEYS_ACCEL_ENABLE  = 0xfe77;
    public static final int OVERLAY_1_ENABLE         = 0xfe78;
    public static final int OVERLAY_2_ENABLE         = 0xfe79;
    public static final int AUDIBLE_BELL_ENABLE      = 0xfe7a;
    
    public static final int POINTER_LEFT              = 0xfee0;
    public static final int POINTER_RIGHT             = 0xfee1;
    public static final int POINTER_UP                = 0xfee2;
    public static final int POINTER_DOWN              = 0xfee3;
    public static final int POINTER_UP_LEFT           = 0xfee4;
    public static final int POINTER_UP_RIGHT          = 0xfee5;
    public static final int POINTER_DOWN_LEFT         = 0xfee6;
    public static final int POINTER_DOWN_RIGHT        = 0xfee7;
    public static final int POINTER_BUTTON_DFLT       = 0xfee8;
    public static final int POINTER_BUTTON_1          = 0xfee9;
    public static final int POINTER_BUTTON_2          = 0xfeea;
    public static final int POINTER_BUTTON_3          = 0xfeeb;
    public static final int POINTER_BUTTON_4          = 0xfeec;
    public static final int POINTER_BUTTON_5          = 0xfeed;
    public static final int POINTER_DOUBLE_CLICK_DFLT = 0xfeee;
    public static final int POINTER_DOUBLE_CLICK_1    = 0xfeef;
    public static final int POINTER_DOUBLE_CLICK_2    = 0xfef0;
    public static final int POINTER_DOUBLE_CLICK_3    = 0xfef1;
    public static final int POINTER_DOUBLE_CLICK_4    = 0xfef2;
    public static final int POINTER_DOUBLE_CLICK_5    = 0xfef3;
    public static final int POINTER_DRAG_DFLT         = 0xfef4;
    public static final int POINTER_DRAG_1            = 0xfef5;
    public static final int POINTER_DRAG_2            = 0xfef6;
    public static final int POINTER_DRAG_3            = 0xfef7;
    public static final int POINTER_DRAG_4            = 0xfef8;
    public static final int POINTER_DRAG_5            = 0xfefd;
    
    public static final int POINTER_ENABLE_KEYS      = 0xfef9;
    public static final int POINTER_ACCELERATE       = 0xfefa;
    public static final int POINTER_DFLT_BUTTON_NEXT = 0xfefb;
    public static final int POINTER_DFLT_BUTTON_PREV = 0xfefc;
    
    
    /* Single-stroke multiple-character n-graph keysyms for the X input method */
    
    public static final int CH_LOWER  = 0xfea0;
    public static final int CH_MIXED  = 0xfea1;
    public static final int CH_UPPER  = 0xfea2;
    public static final int C_H_LOWER = 0xfea3;
    public static final int C_H_MIXED = 0xfea4;
    public static final int C_H_UPPER = 0xfea5;
    
    public static final int TERM_3270_DUPLICATE     = 0xfd01;
    public static final int TERM_3270_FIELD_MARK    = 0xfd02;
    public static final int TERM_3270_RIGHT_2       = 0xfd03;
    public static final int TERM_3270_LEFT_2        = 0xfd04;
    public static final int TERM_3270_BACK_TAB      = 0xfd05;
    public static final int TERM_3270_ERASE_EOF     = 0xfd06;
    public static final int TERM_3270_ERASE_INPUT   = 0xfd07;
    public static final int TERM_3270_RESET         = 0xfd08;
    public static final int TERM_3270_QUIT          = 0xfd09;
    public static final int TERM_3270_PA1           = 0xfd0a;
    public static final int TERM_3270_PA2           = 0xfd0b;
    public static final int TERM_3270_PA3           = 0xfd0c;
    public static final int TERM_3270_TEST          = 0xfd0d;
    public static final int TERM_3270_ATTN          = 0xfd0e;
    public static final int TERM_3270_CURSOR_BLINK  = 0xfd0f;
    public static final int TERM_3270_ALT_CURSOR    = 0xfd10;
    public static final int TERM_3270_KEY_CLICK     = 0xfd11;
    public static final int TERM_3270_JUMP          = 0xfd12;
    public static final int TERM_3270_IDENT         = 0xfd13;
    public static final int TERM_3270_RULE          = 0xfd14;
    public static final int TERM_3270_COPY          = 0xfd15;
    public static final int TERM_3270_PLAY          = 0xfd16;
    public static final int TERM_3270_SETUP         = 0xfd17;
    public static final int TERM_3270_RECORD        = 0xfd18;
    public static final int TERM_3270_CHANGE_SCREEN = 0xfd19;
    public static final int TERM_3270_DELETE_WORD   = 0xfd1a;
    public static final int TERM_3270_EX_SELECT     = 0xfd1b;
    public static final int TERM_3270_CURSOR_SELECT = 0xfd1c;
    public static final int TERM_3270_PRINT_SCREEN  = 0xfd1d;
    public static final int TERM_3270_ENTER         = 0xfd1e;
    
    public static final int TOP_LEFT_SUMMATION           = 0x08b1;
    public static final int BOT_LEFT_SUMMATION           = 0x08b2;
    public static final int TOP_VERT_SUMMATION_CONNECTOR = 0x08b3;
    public static final int BOT_VERT_SUMMATION_CONNECTOR = 0x08b4;
    public static final int TOP_RIGHT_SUMMATION          = 0x08b5;
    public static final int BOT_RIGHT_SUMMATION          = 0x08b6;
    public static final int RIGHT_MIDDLE_SUMMATION       = 0x08b7;
    
    public static final int BLANK = 0x09df;
    
    public static final int MARKER              = 0x0abf;
    public static final int TRADEMARK_IN_CIRCLE = 0x0acb;
    public static final int HEXAGRAM            = 0x0ada;
    public static final int CURSOR              = 0x0aff;
    
    
    /**
     * Hangul start/stop toggle
     */
    public static final int HANGUL = 0xff31;
    
    /**
     * Hangul start
     */
    public static final int HANGUL_START = 0xff32;
    
    /**
     * Hangul end, English start
     */
    public static final int HANGUL_END = 0xff33;
    
    /**
     * Start hangul to hanja conversion
     */
    public static final int HANGUL_HANJA = 0xff34;
    
    /**
     * Hangul jamo mode
     */
    public static final int HANGUL_JAMO = 0xff35;
    
    /**
     * Hangul romaja mode
     */
    public static final int HANGUL_ROMAJA = 0xff36;
    
    /**
     * Hangul code input mode
     */
    public static final int HANGUL_CODEINPUT = 0xff37;
    
    /**
     * Jeonja mode
     */
    public static final int HANGUL_JEONJA = 0xff38;
    
    /**
     * Banja mode
     */
    public static final int HANGUL_BANJA = 0xff39;
    
    /**
     * Pre hanja conversion
     */
    public static final int HANGUL_PRE_HANJA = 0xff3a;
    
    /**
     * Post hanja conversion
     */
    public static final int HANGUL_POST_HANJA = 0xff3b;
    
    /**
     * Single candidate
     */
    public static final int HANGUL_SINGLE_CANDIDATE = 0xff3c;
    
    /**
     * Multiple candidate
     */
    public static final int HANGUL_MULTIPLE_CANDIDATE = 0xff3d;
    
    /**
     * Previous candidate
     */
    public static final int HANGUL_PREVIOUS_CANDIDATE = 0xff3e;
    
    /**
     * Special symbols
     */
    public static final int HANGUL_SPECIAL = 0xff3f;
    
    
    /* Hangul consonant characters */
    
    public static final int HANGUL_KIYEOG      = 0x0ea1;
    public static final int HANGUL_SSANGKIYEOG = 0x0ea2;
    public static final int HANGUL_KIYEOGSIOS  = 0x0ea3;
    public static final int HANGUL_NIEUN       = 0x0ea4;
    public static final int HANGUL_NIEUNJIEUJ  = 0x0ea5;
    public static final int HANGUL_NIEUNHIEUH  = 0x0ea6;
    public static final int HANGUL_DIKEUD      = 0x0ea7;
    public static final int HANGUL_SSANGDIKEUD = 0x0ea8;
    public static final int HANGUL_RIEUL       = 0x0ea9;
    public static final int HANGUL_RIEULKIYEOG = 0x0eaa;
    public static final int HANGUL_RIEULMIEUM  = 0x0eab;
    public static final int HANGUL_RIEULPIEUB  = 0x0eac;
    public static final int HANGUL_RIEULSIOS   = 0x0ead;
    public static final int HANGUL_RIEULTIEUT  = 0x0eae;
    public static final int HANGUL_RIEULPHIEUF = 0x0eaf;
    public static final int HANGUL_RIEULHIEUH  = 0x0eb0;
    public static final int HANGUL_MIEUM       = 0x0eb1;
    public static final int HANGUL_PIEUB       = 0x0eb2;
    public static final int HANGUL_SSANGPIEUB  = 0x0eb3;
    public static final int HANGUL_PIEUBSIOS   = 0x0eb4;
    public static final int HANGUL_SIOS        = 0x0eb5;
    public static final int HANGUL_SSANGSIOS   = 0x0eb6;
    public static final int HANGUL_IEUNG       = 0x0eb7;
    public static final int HANGUL_JIEUJ       = 0x0eb8;
    public static final int HANGUL_SSANGJIEUJ  = 0x0eb9;
    public static final int HANGUL_CIEUC       = 0x0eba;
    public static final int HANGUL_KHIEUQ      = 0x0ebb;
    public static final int HANGUL_TIEUT       = 0x0ebc;
    public static final int HANGUL_PHIEUF      = 0x0ebd;
    public static final int HANGUL_HIEUH       = 0x0ebe;
    
    
    /* Hangul vowel characters */
    
    public static final int HANGUL_A    = 0x0ebf;
    public static final int HANGUL_AE   = 0x0ec0;
    public static final int HANGUL_YA   = 0x0ec1;
    public static final int HANGUL_YAE  = 0x0ec2;
    public static final int HANGUL_EO   = 0x0ec3;
    public static final int HANGUL_E    = 0x0ec4;
    public static final int HANGUL_YEO  = 0x0ec5;
    public static final int HANGUL_YE   = 0x0ec6;
    public static final int HANGUL_O    = 0x0ec7;
    public static final int HANGUL_WA   = 0x0ec8;
    public static final int HANGUL_WAE  = 0x0ec9;
    public static final int HANGUL_OE   = 0x0eca;
    public static final int HANGUL_YO   = 0x0ecb;
    public static final int HANGUL_U    = 0x0ecc;
    public static final int HANGUL_WEO  = 0x0ecd;
    public static final int HANGUL_WE   = 0x0ece;
    public static final int HANGUL_WI   = 0x0ecf;
    public static final int HANGUL_YU   = 0x0ed0;
    public static final int HANGUL_EU   = 0x0ed1;
    public static final int HANGUL_YI   = 0x0ed2;
    public static final int HANGUL_I    = 0x0ed3;
    
    
    /* Hangul syllable-final jongseong characters */
    
    public static final int HANGUL_J_KIYEOG      = 0x0ed4;
    public static final int HANGUL_J_SSANGKIYEOG = 0x0ed5;
    public static final int HANGUL_J_KIYEOGSIOS  = 0x0ed6;
    public static final int HANGUL_J_NIEUN       = 0x0ed7;
    public static final int HANGUL_J_NIEUNJIEUJ  = 0x0ed8;
    public static final int HANGUL_J_NIEUNHIEUH  = 0x0ed9;
    public static final int HANGUL_J_DIKEUD      = 0x0eda;
    public static final int HANGUL_J_RIEUL       = 0x0edb;
    public static final int HANGUL_J_RIEULKIYEOG = 0x0edc;
    public static final int HANGUL_J_RIEULMIEUM  = 0x0edd;
    public static final int HANGUL_J_RIEULPIEUB  = 0x0ede;
    public static final int HANGUL_J_RIEULSIOS   = 0x0edf;
    public static final int HANGUL_J_RIEULTIEUT  = 0x0ee0;
    public static final int HANGUL_J_RIEULPHIEUF = 0x0ee1;
    public static final int HANGUL_J_RIEULHIEUH  = 0x0ee2;
    public static final int HANGUL_J_MIEUM       = 0x0ee3;
    public static final int HANGUL_J_PIEUB       = 0x0ee4;
    public static final int HANGUL_J_PIEUBSIOS   = 0x0ee5;
    public static final int HANGUL_J_SIOS        = 0x0ee6;
    public static final int HANGUL_J_SSANGSIOS   = 0x0ee7;
    public static final int HANGUL_J_IEUNG       = 0x0ee8;
    public static final int HANGUL_J_JIEUJ       = 0x0ee9;
    public static final int HANGUL_J_CIEUC       = 0x0eea;
    public static final int HANGUL_J_KHIEUQ      = 0x0eeb;
    public static final int HANGUL_J_TIEUT       = 0x0eec;
    public static final int HANGUL_J_PHIEUF      = 0x0eed;
    public static final int HANGUL_J_HIEUH       = 0x0eee;
    
    
    /* Ancient Hangul consonant characters */

    public static final int HANGUL_RIEUL_YEORIN_HIEUH  = 0x0eef;
    public static final int HANGUL_SUNKYEONGEUM_MIEUM  = 0x0ef0;
    public static final int HANGUL_SUNKYEONGEUM_PIEUB  = 0x0ef1;
    public static final int HANGUL_PAN_SIOS            = 0x0ef2;
    public static final int HANGUL_KKOGJI_DALRIN_IEUNG = 0x0ef3;
    public static final int HANGUL_SUNKYEONGEUM_PHIEUF = 0x0ef4;
    public static final int HANGUL_YEORIN_HIEUH        = 0x0ef5;
    
    
    /* Ancient Hangul vowel characters */
    
    public static final int HANGUL_ARAE_A  = 0x0ef6;
    public static final int HANGUL_ARAE_AE = 0x0ef7;
    
    
    /* Ancient Hangul syllable-final jongseong characters */
    
    public static final int HANGUL_J_PAN_SIOS            = 0x0ef8;
    public static final int HANGUL_J_KKOGJI_DALRIN_IEUNG = 0x0ef9;
    public static final int HANGUL_J_YEORIN_HIEUH        = 0x0efa;

    
    
    
    /**
     * Constructor hiding
     */
    private XKeyboard()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Gets the key symbol corresponding to a character
     * 
     * @param   character  The character
     * @return             The key symbol
     */
    public static int getKeySymbol(final char character)
    {
	return getKeySymbol((int)character);
    }
    
    /**
     * Gets the key symbol corresponding to a character
     * 
     * @param   character  The character encoded in UTF-16, may only be one actual character
     * @return             The key symbol
     */
    public static int getKeySymbol(final String character)
    {
	if (character.length() == 1)
	    return getKeySymbol((int)(character.charAt(0)));
	if (character.length() != 2)
	    return 0;
	int lead  = (int)(character.charAt(0));
	int trail = (int)(character.charAt(1));
	return 0x10000 + ((lead & ~0xD800) << 8) + (trail & ~0xDC00);
    }
    
    /**
     * Gets the key symbol corresponding to a character
     * 
     * @param   character  The character
     * @return             The key symbol
     */
    public static int getKeySymbol(final int character)
    {
	if (character < 255)
	    return character;
	if ((0x30A1 <= character) && (character <= 0x30A9) && ((character & 1) == 0))
	    return (character - 0x30A1) / 2 + 0x04a7;
	if ((0x30A2 <= character) && (character <= 0x30C8) && ((character & 1) == 0))
	    return (character - 0x30A2) / 2 + 0x04b1;
	if ((0x30CA <= character) && (character <= 0x30CF))  return character + 0x2C05;
	if ((0x30DE <= character) && (character <= 0x30E2))  return character + 0x2C0F;
	if ((0x30E8 <= character) && (character <= 0x30EF))  return character - 0x2C12;
	if ((0x061B <= character) && (character <= 0x0652))  return character + 0x0060;
	if ((0x0454 <= character) && (character <= 0x045C))  return character + 0x0250;
	if ((0x0404 <= character) && (character <= 0x040B))  return character + 0x02B0;
	if ((0x0438 <= character) && (character <= 0x043F))  return character + 0x0291;
	if ((0x0440 <= character) && (character <= 0x0443))  return character + 0x0292;
	if ((0x0418 <= character) && (character <= 0x041f))  return character + 0x02D1;
	if ((0x0420 <= character) && (character <= 0x0423))  return character + 0x02D2;
	if ((0x0388 <= character) && (character <= 0x038a))  return character + 0x041a;
	if ((0x03ac <= character) && (character <= 0x03af))  return character + 0x0405;
	if ((0x03cc <= character) && (character <= 0x03cb))  return character + 0x03ee;
	if ((0x0391 <= character) && (character <= 0x03c9))  return character + 0x0430;
	if ((0x2190 <= character) && (character <= 0x2193))  return character - 0x1895;
	if ((0x2007 <= character) && (character <= 0x2009))  return character - 0x1562;
	if ((0x2153 <= character) && (character <= 0x215a))  return character - 0x16a3;
	if ((0x215b <= character) && (character <= 0x215e))  return character - 0x1698;
	if ((0x05d0 <= character) && (character <= 0x05ea))  return character + 0x0710;
	if ((0x0e01 <= character) && (character <= 0x0e4d))  return character - 0x0060;
	if ((0x0e50 <= character) && (character <= 0x0e59))  return character - 0x0060;
       #while read line; do
       #    out=$(cut -d ' ' -f 1 <<<"${line}")
       #    in=$(cut -d ' ' -f 2 <<<"${line}")
	    if (character == <"$in$">)
		return <"$out$">;
       #done < "src/keysyms"
	return 0x1000000 | character;
    }
    
    
    /**
     * Gets the key symbol from a key code
     * 
     * @param   keycode    The key code
     * @param   group      The group
     * @param   modifiers  The modifers
     * @return             The key symbol
     */
    public static native int toKeySymbol(byte keycode, int group, int modifiers);
    
    /**
     * Gets a key code from a key symbol
     * 
     * @param   keysym  The key symbol
     * @return          The key code
     */
    public static native byte toKeyCode(int keysym);
    
    /**
     * Gets the lower case key symbol from a key symbol
     * 
     * @param   keysym  The key symbol
     * @return          The lower case key symbol
     */
    public static native int toLowerKeySymbol(int keysym);
    
    /**
     * Gets the upper case key symbol from a key symbol
     * 
     * @param   keysym  The key symbol
     * @return          The upper case key symbol
     */
    public static native int toUpperKeySymbol(int keysym);
    
}

