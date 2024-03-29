/*
 * Created on Mar 3, 2005
 */
package de.torstennahm.util;

import java.awt.Color;

/**
 * Provides several classes implementing <code>ColorScale</code>.
 *
 * This class is thread-safe.
 *  
 * @author Torsten Nahm, Jörg Zimmermann
 */

public enum ColorScales implements ColorScale {
	SMOOTHHUE {
		@Override
		protected Color unsafeGetColor(double value) {
			double alpha = 1.5;
		    if (value < .5) {
		    	value = (float) rescale(value, alpha, .25, .25, .5, .5);
		    } else {
		    	value = (float) rescale(value, alpha, .75, .75, 1.0, 1.0);
		    }
			float h = HueMax - (float)value * (HueMax - HueMin);
			return new Color(Color.HSBtoRGB(h, 1f, 1f));
		}
		
		@Override
		public String toString() {
			return "Smoothed hue";
		}
	},
	HUE {
		@Override
		protected Color unsafeGetColor(double value) {
			float h = HueMax - (float)value * (HueMax - HueMin);
			return new Color(Color.HSBtoRGB(h, 1f, 1f));
		}
		
		@Override
		public String toString() {
			return "Hue";
		}
	},
	RGB {
		@Override
		protected Color unsafeGetColor(double value) {
			float r = (float)Math.max((value - 0.6) / 0.4, 0.0);
			float b = (float)Math.max((0.6 - value) / 0.6, 0.0);
			float g = 1 - r - b;
			return new Color(r, g, b);
		}
		
		@Override
		public String toString() {
			return "RGB";
		}
	},
	GREY {
		@Override
		protected Color unsafeGetColor(double value) {
			return new Color((float) value, (float) value, (float) value);
		}
		
		@Override
		public String toString() {
			return "Grey";
		}
	},
	BLACKBODY {
		@Override
		protected Color unsafeGetColor(double value) {
			float r = (float) Math.min(1.0, value * 3);
			float g = (float) Math.max(0.0, Math.min(1.0, value * 3 - 1));
			float b = (float) Math.max(0.0, value * 3 - 2);
			
			return new Color(r, g, b);
		}
		
		@Override
		public String toString() {
			return "Blackbody";
		}
	},
	HEAT {
		@Override
		protected Color unsafeGetColor(double value) {
			return heatColor[Math.min((int)(value * 256), 255)];
		}
		
		@Override
		public String toString() {
			return "Heat";
		}
	};
	
	static private final float HueMin = 0.0f;
	static private final float HueMax = 2.0f / 3f;
	
	public Color getColor(double value) {
		if (value < 0) {
			value = 0;
		} else if (value > 1) {
			value = 1;
		}
		return unsafeGetColor(value);
	}
	
	abstract protected Color unsafeGetColor(double value);
	
	static private double rescale(double x, double alpha, double x1, double y1, double x2, double y2) {
		double u = (x - x1) / (x2 - x1);
		
		if (u >= 0.0) {	// positive branch
			return (y2 - y1) * Math.pow(u, alpha) + y1;
		} else {		// negative branch
			return (y1 - y2) * Math.pow(-u, alpha) + y1;
		}
	}
	
	static private Color heatColor[] = {
		new Color(   0,   0,   0 ),
		new Color(  35,   0,   0 ),
		new Color(  52,   0,   0 ),
		new Color(  60,   0,   0 ),
		new Color(  63,   1,   0 ),
		new Color(  64,   2,   0 ),
		new Color(  68,   5,   0 ),
		new Color(  69,   6,   0 ),
		new Color(  72,   8,   0 ),
		new Color(  74,  10,   0 ),
		new Color(  77,  12,   0 ),
		new Color(  78,  14,   0 ),
		new Color(  81,  16,   0 ),
		new Color(  83,  17,   0 ),
		new Color(  85,  19,   0 ),
		new Color(  86,  20,   0 ),
		new Color(  89,  22,   0 ),
		new Color(  91,  24,   0 ),
		new Color(  92,  25,   0 ),
		new Color(  94,  26,   0 ),
		new Color(  95,  28,   0 ),
		new Color(  98,  30,   0 ),
		new Color( 100,  31,   0 ),
		new Color( 102,  33,   0 ),
		new Color( 103,  34,   0 ),
		new Color( 105,  35,   0 ),
		new Color( 106,  36,   0 ),
		new Color( 108,  38,   0 ),
		new Color( 109,  39,   0 ),
		new Color( 111,  40,   0 ),
		new Color( 112,  42,   0 ),
		new Color( 114,  43,   0 ),
		new Color( 115,  44,   0 ),
		new Color( 117,  45,   0 ),
		new Color( 119,  47,   0 ),
		new Color( 119,  47,   0 ),
		new Color( 120,  48,   0 ),
		new Color( 122,  49,   0 ),
		new Color( 123,  51,   0 ),
		new Color( 125,  52,   0 ),
		new Color( 125,  52,   0 ),
		new Color( 126,  53,   0 ),
		new Color( 128,  54,   0 ),
		new Color( 129,  56,   0 ),
		new Color( 129,  56,   0 ),
		new Color( 131,  57,   0 ),
		new Color( 132,  58,   0 ),
		new Color( 134,  59,   0 ),
		new Color( 134,  59,   0 ),
		new Color( 136,  61,   0 ),
		new Color( 137,  62,   0 ),
		new Color( 137,  62,   0 ),
		new Color( 139,  63,   0 ),
		new Color( 139,  63,   0 ),
		new Color( 140,  65,   0 ),
		new Color( 142,  66,   0 ),
		new Color( 142,  66,   0 ),
		new Color( 143,  67,   0 ),
		new Color( 143,  67,   0 ),
		new Color( 145,  68,   0 ),
		new Color( 145,  68,   0 ),
		new Color( 146,  70,   0 ),
		new Color( 146,  70,   0 ),
		new Color( 148,  71,   0 ),
		new Color( 148,  71,   0 ),
		new Color( 149,  72,   0 ),
		new Color( 149,  72,   0 ),
		new Color( 151,  73,   0 ),
		new Color( 151,  73,   0 ),
		new Color( 153,  75,   0 ),
		new Color( 153,  75,   0 ),
		new Color( 154,  76,   0 ),
		new Color( 154,  76,   0 ),
		new Color( 154,  76,   0 ),
		new Color( 156,  77,   0 ),
		new Color( 156,  77,   0 ),
		new Color( 157,  79,   0 ),
		new Color( 157,  79,   0 ),
		new Color( 159,  80,   0 ),
		new Color( 159,  80,   0 ),
		new Color( 159,  80,   0 ),
		new Color( 160,  81,   0 ),
		new Color( 160,  81,   0 ),
		new Color( 162,  82,   0 ),
		new Color( 162,  82,   0 ),
		new Color( 163,  84,   0 ),
		new Color( 163,  84,   0 ),
		new Color( 165,  85,   0 ),
		new Color( 165,  85,   0 ),
		new Color( 166,  86,   0 ),
		new Color( 166,  86,   0 ),
		new Color( 166,  86,   0 ),
		new Color( 168,  87,   0 ),
		new Color( 168,  87,   0 ),
		new Color( 170,  89,   0 ),
		new Color( 170,  89,   0 ),
		new Color( 171,  90,   0 ),
		new Color( 171,  90,   0 ),
		new Color( 173,  91,   0 ),
		new Color( 173,  91,   0 ),
		new Color( 174,  93,   0 ),
		new Color( 174,  93,   0 ),
		new Color( 176,  94,   0 ),
		new Color( 176,  94,   0 ),
		new Color( 177,  95,   0 ),
		new Color( 177,  95,   0 ),
		new Color( 179,  96,   0 ),
		new Color( 179,  96,   0 ),
		new Color( 180,  98,   0 ),
		new Color( 182,  99,   0 ),
		new Color( 182,  99,   0 ),
		new Color( 183, 100,   0 ),
		new Color( 183, 100,   0 ),
		new Color( 185, 102,   0 ),
		new Color( 185, 102,   0 ),
		new Color( 187, 103,   0 ),
		new Color( 187, 103,   0 ),
		new Color( 188, 104,   0 ),
		new Color( 188, 104,   0 ),
		new Color( 190, 105,   0 ),
		new Color( 191, 107,   0 ),
		new Color( 191, 107,   0 ),
		new Color( 193, 108,   0 ),
		new Color( 193, 108,   0 ),
		new Color( 194, 109,   0 ),
		new Color( 196, 110,   0 ),
		new Color( 196, 110,   0 ),
		new Color( 197, 112,   0 ),
		new Color( 197, 112,   0 ),
		new Color( 199, 113,   0 ),
		new Color( 200, 114,   0 ),
		new Color( 200, 114,   0 ),
		new Color( 202, 116,   0 ),
		new Color( 202, 116,   0 ),
		new Color( 204, 117,   0 ),
		new Color( 205, 118,   0 ),
		new Color( 205, 118,   0 ),
		new Color( 207, 119,   0 ),
		new Color( 208, 121,   0 ),
		new Color( 208, 121,   0 ),
		new Color( 210, 122,   0 ),
		new Color( 211, 123,   0 ),
		new Color( 211, 123,   0 ),
		new Color( 213, 124,   0 ),
		new Color( 214, 126,   0 ),
		new Color( 214, 126,   0 ),
		new Color( 216, 127,   0 ),
		new Color( 217, 128,   0 ),
		new Color( 217, 128,   0 ),
		new Color( 219, 130,   0 ),
		new Color( 221, 131,   0 ),
		new Color( 221, 131,   0 ),
		new Color( 222, 132,   0 ),
		new Color( 224, 133,   0 ),
		new Color( 224, 133,   0 ),
		new Color( 225, 135,   0 ),
		new Color( 227, 136,   0 ),
		new Color( 227, 136,   0 ),
		new Color( 228, 137,   0 ),
		new Color( 230, 138,   0 ),
		new Color( 230, 138,   0 ),
		new Color( 231, 140,   0 ),
		new Color( 233, 141,   0 ),
		new Color( 233, 141,   0 ),
		new Color( 234, 142,   0 ),
		new Color( 236, 144,   0 ),
		new Color( 236, 144,   0 ),
		new Color( 238, 145,   0 ),
		new Color( 239, 146,   0 ),
		new Color( 241, 147,   0 ),
		new Color( 241, 147,   0 ),
		new Color( 242, 149,   0 ),
		new Color( 244, 150,   0 ),
		new Color( 244, 150,   0 ),
		new Color( 245, 151,   0 ),
		new Color( 247, 153,   0 ),
		new Color( 247, 153,   0 ),
		new Color( 248, 154,   0 ),
		new Color( 250, 155,   0 ),
		new Color( 251, 156,   0 ),
		new Color( 251, 156,   0 ),
		new Color( 253, 158,   0 ),
		new Color( 255, 159,   0 ),
		new Color( 255, 159,   0 ),
		new Color( 255, 160,   0 ),
		new Color( 255, 161,   0 ),
		new Color( 255, 163,   0 ),
		new Color( 255, 163,   0 ),
		new Color( 255, 164,   0 ),
		new Color( 255, 165,   0 ),
		new Color( 255, 167,   0 ),
		new Color( 255, 167,   0 ),
		new Color( 255, 168,   0 ),
		new Color( 255, 169,   0 ),
		new Color( 255, 169,   0 ),
		new Color( 255, 170,   0 ),
		new Color( 255, 172,   0 ),
		new Color( 255, 173,   0 ),
		new Color( 255, 173,   0 ),
		new Color( 255, 174,   0 ),
		new Color( 255, 175,   0 ),
		new Color( 255, 177,   0 ),
		new Color( 255, 178,   0 ),
		new Color( 255, 179,   0 ),
		new Color( 255, 181,   0 ),
		new Color( 255, 181,   0 ),
		new Color( 255, 182,   0 ),
		new Color( 255, 183,   0 ),
		new Color( 255, 184,   0 ),
		new Color( 255, 187,   7 ),
		new Color( 255, 188,  10 ),
		new Color( 255, 189,  14 ),
		new Color( 255, 191,  18 ),
		new Color( 255, 192,  21 ),
		new Color( 255, 193,  25 ),
		new Color( 255, 195,  29 ),
		new Color( 255, 197,  36 ),
		new Color( 255, 198,  40 ),
		new Color( 255, 200,  43 ),
		new Color( 255, 202,  51 ),
		new Color( 255, 204,  54 ),
		new Color( 255, 206,  61 ),
		new Color( 255, 207,  65 ),
		new Color( 255, 210,  72 ),
		new Color( 255, 211,  76 ),
		new Color( 255, 214,  83 ),
		new Color( 255, 216,  91 ),
		new Color( 255, 219,  98 ),
		new Color( 255, 221, 105 ),
		new Color( 255, 223, 109 ),
		new Color( 255, 225, 116 ),
		new Color( 255, 228, 123 ),
		new Color( 255, 232, 134 ),
		new Color( 255, 234, 142 ),
		new Color( 255, 237, 149 ),
		new Color( 255, 239, 156 ),
		new Color( 255, 240, 160 ),
		new Color( 255, 243, 167 ),
		new Color( 255, 246, 174 ),
		new Color( 255, 248, 182 ),
		new Color( 255, 249, 185 ),
		new Color( 255, 252, 193 ),
		new Color( 255, 253, 196 ),
		new Color( 255, 255, 204 ),
		new Color( 255, 255, 207 ),
		new Color( 255, 255, 211 ),
		new Color( 255, 255, 218 ),
		new Color( 255, 255, 222 ),
		new Color( 255, 255, 225 ),
		new Color( 255, 255, 229 ),
		new Color( 255, 255, 233 ),
		new Color( 255, 255, 236 ),
		new Color( 255, 255, 240 ),
		new Color( 255, 255, 244 ),
		new Color( 255, 255, 247 ),
		new Color( 255, 255, 255 )
	};
}
