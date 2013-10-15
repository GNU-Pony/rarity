#include <stdio.h>
#include <X11/extensions/Xrandr.h>

#include "Monitor.h"
#include "global.h"


#define false  0


/**
 * The selected screen's index
 */
static int screen;

/**
 * The root window for the selected screen
 */
static Window root;

/**
 * Resources for the selected screen
 */
static XRRScreenResources* res;

/**
 * The primary monitor for the selected screen
 */
static RROutput primary;

/**
 * The selected monitor's index under its screen
 */
static int output;

/**
 * Information for the selected monitor
 */
static XRROutputInfo* output_info;

/**
 * CRT controller (quite the legacy name, but everypony likes a good CRT monitor)
 * Information for the selected monitor
 */
static XRRCrtcInfo* output_crtc;

/**
 * Mode information for the selected monitor
 */
static XRRModeInfo* output_mode;



/**
 * Initialise update process
 */
void Java_rarity_Monitor_begin(JNIEnv* env, jclass class)
{
  int _1, _2;
  
  (void) env;
  (void) class;
  
  if (display == NULL)
    {
      fprintf(stderr, "X display has not been opened yet\n");
      abort();
    }
  if (XRRQueryExtension(display, &_1, &_2) == false)
    {
      fprintf(stderr, "Missing RandR extension\n");
      abort();
    }
}


/**
 * The number of screens in the environment
 * 
 * @return  The number of screens in the environment
 */
jint Java_rarity_Monitor_getScreenCount(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return ScreenCount(display);
}

/**
 * Select a screen
 * 
 * @param  screen  The index of the screen
 */
void Java_rarity_Monitor_selectScreen(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  
  screen = index;
  
  root = RootWindow(display, screen);
  primary = XRRGetOutputPrimary(display, root);
  
  if ((res = XRRGetScreenResources(display, root)) == false)
    {
      fprintf(stderr, "Cannot get screen resources\n");
      abort();
    }
}

/**
 * The number of monitors in the environment for the selected screen
 * 
 * @return  The number of monitors in the environment for the selected screen
 */
jint Java_rarity_Monitor_getScreenMonitorCount(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return res->noutput;
}

/**
 * Select a monitor
 * 
 * @param  monitor  The index of the monitor under the selected screen
 */
void Java_rarity_Monitor_selectMonitor(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  
  output = index;
  output_info = XRRGetOutputInfo(display, res, res->outputs[output]);
}


/**
 * Checks whether the selected monitor is connected.
 * This check will initialise data required to get resultion and offset.
 * 
 * @return  Whether the selected monitor is connected
 */
jboolean Java_rarity_Monitor_isConnected(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  if (output_info->connection == 1)
    return 0;
  
  output_crtc = XRRGetCrtcInfo(display, res, output_info->crtc);
  output_mode = res->modes + output;
  
  return 1;
}

/**
 * Checks whether the selected monitor is the primary monitor its screen
 * 
 * @return  Whether the selected monitor is the primary monitor its screen
 */
jboolean Java_rarity_Monitor_isPrimary(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return primary == res->outputs[output];
}


/**
 * Gets the selected monitor's virtual resolution width
 * 
 * @return  The selected monitor's virtual resolution width
 */
jint Java_rarity_Monitor_getWidth(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return output_mode->width;
}

/**
 * Gets the selected monitor's virtual resolution height
 * 
 * @return  The selected monitor's virtual resolution height
 */
jint Java_rarity_Monitor_getHeight(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return output_mode->height;
}

/**
 * Gets the selected monitor's offset on the X-axis from its screens top left corner
 * 
 * @return  The selected monitor's offset on the X-axis from its screens top left corner
 */
jint Java_rarity_Monitor_getXOffset(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return output_crtc->x;
}

/**
 * Gets the selected monitor's offset on the Y-axis from its screens top left corner
 * 
 * @return  The selected monitor's offset on the Y-axis from its screens top left corner
 */
jint Java_rarity_Monitor_getYOffset(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  return output_crtc->y;
}

