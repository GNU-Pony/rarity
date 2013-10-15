#include <stdio.h>
#include <X11/Xlib.h>
#include <X11/Xlibint.h>
#include <X11/Xproto.h>
#include <X11/Xatom.h>
#include <X11/extensions/Xrandr.h>
#include <X11/extensions/Xrender.h>


#define false  0
#define true   1


static Display* display;
static int screen_count;


int main()
{
  int screen, _1, _2;
  
  display = XOpenDisplay(NULL);
  if (display == NULL)
    {
      fprintf(stderr, "Cannot open X display\n");
      return 1;
    }
  if (XRRQueryExtension(display, &_1, &_2) == false)
    {
      fprintf(stderr, "Missing RandR extension\n");
      return 1;
    }
  
  screen_count = ScreenCount(display);
  for (screen = 0; screen < screen_count; screen++)
    {
      Window root = RootWindow(display, screen);
      XRRScreenResources* res;
      
      if ((res = XRRGetScreenResources(display, root)) == false)
	{
	  fprintf(stderr, "Cannot get screen resources\n");
	  return 1;
	}
    }
  
  return 0;
}

