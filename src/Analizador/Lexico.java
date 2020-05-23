package Analizador;
import java_cup.runtime.Symbol;


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yychar = 1; 
    yyline = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NO_ANCHOR,
		/* 232 */ YY_NO_ANCHOR,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NO_ANCHOR,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NO_ANCHOR,
		/* 247 */ YY_NO_ANCHOR,
		/* 248 */ YY_NO_ANCHOR,
		/* 249 */ YY_NO_ANCHOR,
		/* 250 */ YY_NO_ANCHOR,
		/* 251 */ YY_NO_ANCHOR,
		/* 252 */ YY_NO_ANCHOR,
		/* 253 */ YY_NO_ANCHOR,
		/* 254 */ YY_NO_ANCHOR,
		/* 255 */ YY_NO_ANCHOR,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NO_ANCHOR,
		/* 260 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"56,58:8,51,50,58,55,4,58:18,55,22,57,58:2,16,23,54,7,8,3,14,12,15,49,1,52:1" +
"0,26,11,21,13,20,27,47,38,41,37,39,34,33,43,36,29,53,42,32,46,30,18,17,53,2" +
"8,35,31,40,44,19,45,53:2,9,2,10,25,48,58,38,41,37,39,34,33,43,36,29,53,42,3" +
"2,46,30,18,17,53,28,35,31,40,44,19,45,53:2,5,24,6,58:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,261,
"0,1,2,1:2,3,1:8,4,5,6,1,7,8,9,10,1:6,11,12,1:8,13,14,15,1,13:4,16,17,1,18,1" +
"3:7,19,13:29,20,21,22,1,23,24,17,25,26,27,28,29,23:2,30,31,32,33,13,34,35,3" +
"6,37,24,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,6" +
"0,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,8" +
"5,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107" +
",108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,39,125" +
",126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,14" +
"4,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,1" +
"63,164,165,166,167,168,169,170,171,172,173,13,174,175,176,177,178,179,180,1" +
"81,182,183,184,185")[0];

	private int yy_nxt[][] = unpackFromString(186,59,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,197,224,19,20,21,88,94,22,23,2" +
"4,237,89,244,247,248,249,250,251,248,252,253,95,248,254,248,255,256,248:2,2" +
"5,248,26,27,5,28,248,97,5,3,100,3,-1:60,29,-1,87,-1:59,5,-1:46,5,-1:3,5,-1:" +
"16,30,-1:59,31,-1:59,32,-1:60,248,98,248,-1:8,101,248:11,103,248:6,-1,105,-" +
"1:3,107,248,-1:18,33,-1:58,34,-1:58,35,-1:94,93,-1:2,28,-1:7,29:3,-1,29:45," +
"-1,29:8,-1:17,248:3,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:12" +
",142,248:6,-1,105,-1:3,107,248,-1:55,106:2,-1:4,106:2,-1:18,248:3,-1:8,248," +
"217,248:17,-1,105,-1:3,107,248,-1:57,47,-1:23,248:3,-1:8,248:19,-1,166,-1:3" +
",107,248,-1:22,248:3,-1:8,248:3,235,178,248:14,-1,105,-1:3,107,248,-1:6,87:" +
"2,104,87:55,-1:23,36,-1:52,248:3,-1:8,248:2,114,248:2,38,248:12,115,-1,105," +
"-1:3,107,248,-1:6,99,102,99:47,-1,99:6,41,99,-1,112,-1:81,37,-1:51,248,39,2" +
"48,-1:8,248:6,126,248:12,-1,105,-1:3,107,248,-1:59,90,-1:5,96:49,-1:2,96:2," +
"40,96:4,-1:17,248:2,42,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248,257,248,-1" +
":8,248,128,248:17,-1,105,-1:3,107,248,-1:6,99,102,99:47,-1,99:6,91,99,-1:17" +
",248:3,-1:8,248:13,226,248:5,-1,105,-1:3,107,248,-1:6,48,198,108,198:55,-1:" +
"54,96,-1:21,107:3,-1:8,107:19,-1,107,-1:3,107:2,-1:6,92,87,104,87:55,-1:17," +
"248:3,-1:8,248:6,129,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,207,24" +
"8:17,-1,105,-1:3,107,248,-1:6,110,87,104,87:55,-1:17,248:3,-1:8,248:3,204,2" +
"48:6,130,248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,43,248:15,-1,105," +
"-1:3,107,248,-1:22,209,248:2,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:2,44" +
",-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,131,248:14,-1,105,-" +
"1:3,107,248,-1:22,248:3,-1:8,248,227,248:7,132,248,230,248:7,-1,105,-1:3,10" +
"7,248,-1:22,248:3,-1:8,248:12,133,248:6,-1,105,-1:3,107,248,-1:22,248:3,-1:" +
"8,45,248:18,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2,241,248:16,-1,105,-1" +
":3,107,248,-1:22,248:3,-1:8,248:7,135,248:11,-1,105,-1:3,107,248,-1:22,248:" +
"3,-1:8,248:3,136,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,46,248:9,212,2" +
"48:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,138,248:8,-1,105,-1:3,107," +
"248,-1:22,248:3,-1:8,248:5,143,248:13,-1,105,-1:3,107,248,-1:22,248,144,248" +
",-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2,147,248:13,233,248:" +
"2,-1,105,-1:3,107,248,-1:22,248:3,-1:8,210,248:18,-1,105,-1:3,107,248,-1:22" +
",248:3,-1:8,248:11,49,248:7,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,50,2" +
"48:14,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:8,243,248:10,-1,105,-1:3,107" +
",248,-1:22,248:3,-1:8,248:6,51,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8," +
"248:7,52,248:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,53,248:12,-1,105" +
",-1:3,107,248,-1:22,248:3,-1:8,248:6,155,248:12,-1,105,-1:3,107,248,-1:22,2" +
"48:3,-1:8,248:3,156,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:7,158,2" +
"48:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,54,248:18,-1,105,-1:3,107,248,-1" +
":22,248:3,-1:8,248:6,55,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,2" +
"46,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:13,214,248:5,-1,105,-1:3" +
",107,248,-1:22,248:3,-1:8,248:10,216,248:8,-1,105,-1:3,107,248,-1:22,248:3," +
"-1:8,248:4,159,248:14,-1,105,-1:3,107,248,-1:22,161,248:2,-1:8,248:19,-1,10" +
"5,-1:3,107,248,-1:22,248:3,-1:8,248:11,56,248:7,-1,105,-1:3,107,248,-1:22,2" +
"48:3,-1:8,248:3,57,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,163,248:" +
"17,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,199,248:12,-1,105,-1:3,107,24" +
"8,-1:22,248:3,-1:8,248:6,58,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248" +
":3,59,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:12,169,248:6,-1,105,-" +
"1:3,107,248,-1:22,248:3,-1:8,248:4,60,248:14,-1,105,-1:3,107,248,-1:22,248:" +
"3,-1:8,248:6,61,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2,170,248:1" +
"6,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:9,171,248:9,-1,105,-1:3,107,248," +
"-1:22,248:3,-1:8,62,248:18,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:7,63,24" +
"8:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,221,248:12,-1,105,-1:3,107," +
"248,-1:22,248:3,-1:8,248:14,64,248:4,-1,105,-1:3,107,248,-1:22,248:3,-1:8,2" +
"48:8,65,248:10,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,179,248:15,-1,105" +
",-1:3,107,248,-1:22,248:3,-1:8,248:9,66,248:9,-1,105,-1:3,107,248,-1:22,248" +
":3,-1:8,248,220,248:17,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2,67,248:16" +
",-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:5,180,248:13,-1,105,-1:3,107,248," +
"-1:22,248:3,-1:8,248:3,68,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,69,24" +
"8:18,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:13,222,248:5,-1,105,-1:3,107," +
"248,-1:22,248:3,-1:8,248:11,181,248:7,-1,105,-1:3,107,248,-1:22,248:3,-1:8," +
"248:8,70,248:10,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:15,71,248:3,-1,105" +
",-1:3,107,248,-1:22,248:3,-1:8,248:9,72,248:9,-1,105,-1:3,107,248,-1:22,248" +
":3,-1:8,248:2,182,248:16,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,183,24" +
"8:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,73,248:12,-1,105,-1:3,107,24" +
"8,-1:22,248:3,-1:8,248:4,184,248:14,-1,105,-1:3,107,248,-1:22,248:3,-1:8,24" +
"8:2,74,248:16,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,75,248:12,-1,105,-" +
"1:3,107,248,-1:22,248:3,-1:8,248,189,248:17,-1,105,-1:3,107,248,-1:22,248:3" +
",-1:8,248:7,76,248:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:12,191,248:6" +
",-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:9,192,248:9,-1,105,-1:3,107,248,-" +
"1:22,248:3,-1:8,248:3,77,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2," +
"78,248:16,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,193,248:12,-1,105,-1:3" +
",107,248,-1:22,248:3,-1:8,248:13,223,248:5,-1,105,-1:3,107,248,-1:22,248:3," +
"-1:8,248:6,79,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,194,248:14," +
"-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,80,248:12,-1,105,-1:3,107,248,-1" +
":22,248:3,-1:8,248:6,81,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,8" +
"2,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:11,83,248:7,-1,105,-1:3,1" +
"07,248,-1:22,248:3,-1:8,248:6,84,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:" +
"8,248:10,85,248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,86,248:12,-1,1" +
"05,-1:3,107,248,-1:22,248:3,-1:8,248:16,109,248:2,-1,105,-1:3,107,248,-1:22" +
",248:3,-1:8,248:19,-1,260,-1:3,107,248,-1:22,248:3,-1:8,248:6,213,248:12,-1" +
",105,-1:3,107,248,-1:22,248:3,-1:8,248,239,248:17,-1,105,-1:3,107,248,-1:22" +
",211,248:2,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,240,248:1" +
"4,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:12,231,248:6,-1,105,-1:3,107,248" +
",-1:22,248:3,-1:8,248:2,259,248:16,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248" +
":7,140,248:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,149,248:15,-1,105," +
"-1:3,107,248,-1:22,248:3,-1:8,248:10,139,248:8,-1,105,-1:3,107,248,-1:22,24" +
"8,242,248,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,164,248:18,-1,10" +
"5,-1:3,107,248,-1:22,248:3,-1:8,248:6,157,248:12,-1,105,-1:3,107,248,-1:22," +
"248:3,-1:8,248:3,215,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,160" +
",248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,176,248:14,-1,105,-1:3,10" +
"7,248,-1:22,248:3,-1:8,248,173,248:17,-1,105,-1:3,107,248,-1:22,248:3,-1:8," +
"248:12,177,248:6,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2,172,248:16,-1,1" +
"05,-1:3,107,248,-1:22,248:3,-1:8,248:9,219,248:9,-1,105,-1:3,107,248,-1:22," +
"248:3,-1:8,248:3,186,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:11,188" +
",248:7,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,185,248:8,-1,105,-1:3,10" +
"7,248,-1:22,248:3,-1:8,248:4,190,248:14,-1,105,-1:3,107,248,-1:22,248:3,-1:" +
"8,248:4,195,248:14,-1,105,-1:3,107,248,-1:22,248:3,-1:8,111,248:7,201,248:1" +
"0,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,134,248:17,-1,105,-1:3,107,248,-" +
"1:22,248:3,-1:8,248:4,148,248:14,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:2" +
",151,248:16,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:7,141,248:11,-1,105,-1" +
":3,107,248,-1:22,248:3,-1:8,248:10,145,248:8,-1,105,-1:3,107,248,-1:22,248," +
"152,248,-1:8,248:19,-1,105,-1:3,107,248,-1:22,248:3,-1:8,165,248:18,-1,105," +
"-1:3,107,248,-1:22,248:3,-1:8,248:6,218,248:12,-1,105,-1:3,107,248,-1:22,24" +
"8:3,-1:8,248:10,162,248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,174,248:" +
"17,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,187,248:8,-1,105,-1:3,107,24" +
"8,-1:22,248:3,-1:8,248:4,196,248:14,-1,105,-1:3,107,248,-1:22,248:3,-1:8,24" +
"8:6,113,248:12,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,137,248:17,-1,105,-" +
"1:3,107,248,-1:22,248:3,-1:8,248:4,150,248:14,-1,105,-1:3,107,248,-1:22,248" +
":3,-1:8,248:7,154,248:11,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:10,153,24" +
"8:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,167,248:18,-1,105,-1:3,107,248,-1:" +
"22,248:3,-1:8,248:10,168,248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:6,1" +
"16,248:5,117,248:6,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248,146,248:17,-1,1" +
"05,-1:3,107,248,-1:22,248:3,-1:8,175,248:18,-1,105,-1:3,107,248,-1:22,248,1" +
"18,248,-1:8,119,248:7,225,248:10,-1,105,-1:3,107,248,-1:22,248,120,248,-1:8" +
",248,121,248:8,203,248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:4,122,248" +
":12,123,248,-1,105,-1:3,107,248,-1:22,248:2,238,-1:8,248:3,124,248:8,202,24" +
"8:6,-1,105,-1:3,107,248,-1:22,248,205,248,-1:8,248:4,125,248:3,208,248,206," +
"248:8,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:13,228,248:5,-1,105,-1:3,107" +
",248,-1:22,248,127,248,-1:8,200,248:18,-1,105,-1:3,107,248,-1:22,248:3,-1:8" +
",229,248:18,-1,105,-1:3,107,248,-1:22,248,245,248,-1:8,248:19,-1,105,-1:3,1" +
"07,248,-1:22,248:3,-1:8,248:3,232,248:15,-1,105,-1:3,107,248,-1:22,248:3,-1" +
":8,248,236,248:17,-1,105,-1:3,107,248,-1:22,248:3,-1:8,248:3,234,248:15,-1," +
"105,-1:3,107,248,-1:22,248:3,-1:8,248:5,258,248:13,-1,105,-1:3,107,248,-1:5");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.div,yychar,yyline, yytext());}
					case -3:
						break;
					case 3:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.por,yychar,yyline, yytext());}
					case -5:
						break;
					case 5:
						{ /* Se ignoran */}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.llaveabre,yychar,yyline, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.llavecierra,yychar,yyline, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.parizq, yychar,yyline , yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.parder, yychar,yyline , yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.corcheteizq, yychar,yyline , yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.corcheteder, yychar,yyline , yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.pcoma, yychar,yyline , yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.coma, yychar,yyline , yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.igual, yychar,yyline , yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.mas,yychar,yyline, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.menos,yychar,yyline, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.mod,yychar,yyline, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.mayor, yychar,yyline , yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.menor, yychar,yyline , yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.not, yychar,yyline , yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.xor,yychar,yyline, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.dospuntos, yychar,yyline , yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.interrogacion, yychar,yyline , yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.arroba,yychar,yyline, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.punto,yychar,yyline, yytext());}
					case -27:
						break;
					case 27:
						{yychar=1;}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.numero, yychar,yyline,yytext());}
					case -29:
						break;
					case 29:
						{}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.igualacion, yychar,yyline , yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.aumento, yychar,yyline , yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.decremento, yychar,yyline , yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.mayorigual, yychar,yyline , yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.menorigual, yychar,yyline , yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.diferente, yychar,yyline , yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.and, yychar,yyline , yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.or, yychar,yyline , yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.si, yychar,yyline , yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.hacer, yychar,yyline , yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.caracter,yychar,yyline, yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.cadena,yychar,yyline, yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.potencia, yychar,yyline , yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.entero,yychar,yyline, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.nuevo,yychar,yyline, yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.para, yychar,yyline , yytext());}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.caststr,yychar,yyline, yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.decimal,yychar,yyline, yytext());}
					case -48:
						break;
					case 48:
						{}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.leer,yychar,yyline, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.nulo,yychar,yyline, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.verdadero,yychar,yyline, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.este,yychar,yyline, yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.sino, yychar,yyline , yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.character,yychar,yyline, yytext());}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.caso, yychar,yyline , yytext());}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.rvoid,yychar,yyline, yytext());}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.imprimir, yychar,yyline , yytext());}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.mientras, yychar,yyline , yytext());}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.toint,yychar,yyline, yytext());}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.rfinal,yychar,yyline, yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.falso,yychar,yyline, yytext());}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.superior,yychar,yyline, yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.clase,yychar,yyline, yytext());}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.quiebre, yychar,yyline , yytext());}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.graficar,yychar,yyline, yytext());}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.publico,yychar,yyline, yytext());}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.retorno,yychar,yyline, yytext());}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.importar,yychar,yyline, yytext());}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.tochar,yychar,yyline, yytext());}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.seleccion, yychar,yyline , yytext());}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.cadenaString,yychar,yyline, yytext());}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.estatico,yychar,yyline, yytext());}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.doble,yychar,yyline, yytext());}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.imprimirconlinea, yychar,yyline , yytext());}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.privado,yychar,yyline, yytext());}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.extender,yychar,yyline, yytext());}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.defecto, yychar,yyline , yytext());}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.booleano,yychar,yyline, yytext());}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.sobreescribir,yychar,yyline, yytext());}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.todouble,yychar,yyline, yytext());}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.continuar, yychar,yyline , yytext());}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.abstracto,yychar,yyline, yytext());}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.protegido,yychar,yyline, yytext());}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.leerarchivo,yychar,yyline, yytext());}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.printtabla,yychar,yyline, yytext());}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.escribirarchivo,yychar,yyline, yytext());}
					case -87:
						break;
					case 88:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -88:
						break;
					case 89:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -89:
						break;
					case 90:
						{return new Symbol(sym.caracter,yychar,yyline, yytext());}
					case -90:
						break;
					case 91:
						{return new Symbol(sym.cadena,yychar,yyline, yytext());}
					case -91:
						break;
					case 92:
						{}
					case -92:
						break;
					case 94:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -93:
						break;
					case 95:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -94:
						break;
					case 97:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -95:
						break;
					case 98:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -96:
						break;
					case 100:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -97:
						break;
					case 101:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -98:
						break;
					case 103:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -99:
						break;
					case 105:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -100:
						break;
					case 107:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -101:
						break;
					case 109:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -102:
						break;
					case 111:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -103:
						break;
					case 113:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -104:
						break;
					case 114:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -105:
						break;
					case 115:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -106:
						break;
					case 116:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -107:
						break;
					case 117:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -108:
						break;
					case 118:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -109:
						break;
					case 119:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -110:
						break;
					case 120:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -111:
						break;
					case 121:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -112:
						break;
					case 122:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -113:
						break;
					case 123:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -114:
						break;
					case 124:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -115:
						break;
					case 125:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -116:
						break;
					case 126:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -117:
						break;
					case 127:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -118:
						break;
					case 128:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -119:
						break;
					case 129:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -120:
						break;
					case 130:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -121:
						break;
					case 131:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -122:
						break;
					case 132:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -123:
						break;
					case 133:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -124:
						break;
					case 134:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -125:
						break;
					case 135:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -126:
						break;
					case 136:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -127:
						break;
					case 137:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -128:
						break;
					case 138:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -129:
						break;
					case 139:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -130:
						break;
					case 140:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -131:
						break;
					case 141:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -132:
						break;
					case 142:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -133:
						break;
					case 143:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -134:
						break;
					case 144:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -135:
						break;
					case 145:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -136:
						break;
					case 146:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -137:
						break;
					case 147:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -138:
						break;
					case 148:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -139:
						break;
					case 149:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -140:
						break;
					case 150:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -141:
						break;
					case 151:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -142:
						break;
					case 152:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -143:
						break;
					case 153:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -144:
						break;
					case 154:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -145:
						break;
					case 155:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -146:
						break;
					case 156:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -147:
						break;
					case 157:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -148:
						break;
					case 158:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -149:
						break;
					case 159:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -150:
						break;
					case 160:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -151:
						break;
					case 161:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -152:
						break;
					case 162:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -153:
						break;
					case 163:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -154:
						break;
					case 164:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -155:
						break;
					case 165:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -156:
						break;
					case 166:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -157:
						break;
					case 167:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -158:
						break;
					case 168:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -159:
						break;
					case 169:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -160:
						break;
					case 170:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -161:
						break;
					case 171:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -162:
						break;
					case 172:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -163:
						break;
					case 173:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -164:
						break;
					case 174:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -165:
						break;
					case 175:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -166:
						break;
					case 176:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -167:
						break;
					case 177:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -168:
						break;
					case 178:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -169:
						break;
					case 179:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -170:
						break;
					case 180:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -171:
						break;
					case 181:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -172:
						break;
					case 182:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -173:
						break;
					case 183:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -174:
						break;
					case 184:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -175:
						break;
					case 185:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -176:
						break;
					case 186:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -177:
						break;
					case 187:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -178:
						break;
					case 188:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -179:
						break;
					case 189:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -180:
						break;
					case 190:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -181:
						break;
					case 191:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -182:
						break;
					case 192:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -183:
						break;
					case 193:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -184:
						break;
					case 194:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -185:
						break;
					case 195:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -186:
						break;
					case 196:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -187:
						break;
					case 197:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -188:
						break;
					case 199:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -189:
						break;
					case 200:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -190:
						break;
					case 201:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -191:
						break;
					case 202:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -192:
						break;
					case 203:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -193:
						break;
					case 204:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -194:
						break;
					case 205:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -195:
						break;
					case 206:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -196:
						break;
					case 207:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -197:
						break;
					case 208:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -198:
						break;
					case 209:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -199:
						break;
					case 210:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -200:
						break;
					case 211:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -201:
						break;
					case 212:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -202:
						break;
					case 213:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -203:
						break;
					case 214:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -204:
						break;
					case 215:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -205:
						break;
					case 216:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -206:
						break;
					case 217:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -207:
						break;
					case 218:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -208:
						break;
					case 219:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -209:
						break;
					case 220:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -210:
						break;
					case 221:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -211:
						break;
					case 222:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -212:
						break;
					case 223:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -213:
						break;
					case 224:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -214:
						break;
					case 225:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -215:
						break;
					case 226:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -216:
						break;
					case 227:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -217:
						break;
					case 228:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -218:
						break;
					case 229:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -219:
						break;
					case 230:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -220:
						break;
					case 231:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -221:
						break;
					case 232:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -222:
						break;
					case 233:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -223:
						break;
					case 234:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -224:
						break;
					case 235:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -225:
						break;
					case 236:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -226:
						break;
					case 237:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -227:
						break;
					case 238:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -228:
						break;
					case 239:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -229:
						break;
					case 240:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -230:
						break;
					case 241:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -231:
						break;
					case 242:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -232:
						break;
					case 243:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -233:
						break;
					case 244:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -234:
						break;
					case 245:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -235:
						break;
					case 246:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -236:
						break;
					case 247:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -237:
						break;
					case 248:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -238:
						break;
					case 249:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -239:
						break;
					case 250:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -240:
						break;
					case 251:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -241:
						break;
					case 252:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -242:
						break;
					case 253:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -243:
						break;
					case 254:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -244:
						break;
					case 255:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -245:
						break;
					case 256:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -246:
						break;
					case 257:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -247:
						break;
					case 258:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -248:
						break;
					case 259:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -249:
						break;
					case 260:
						{return new Symbol(sym.identificador,yychar,yyline, yytext());}
					case -250:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
