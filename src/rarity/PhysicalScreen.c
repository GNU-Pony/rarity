#include <stdio.h>
#include <X11/extensions/Xrandr.h>


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
      int outputs, output;
      
      RROutput primary = XRRGetOutputPrimary(display, root);
      
      if ((res = XRRGetScreenResources(display, root)) == false)
	{
	  fprintf(stderr, "Cannot get screen resources\n");
	  return 1;
	}
      
      outputs = res->noutput;
      for (output = 0; output < outputs; output++)
	{
	  XRROutputInfo* output_info = XRRGetOutputInfo(display, res, res->outputs[output]);
	  if (output_info->connection == 1)
	    continue; /* not connected */
	  
	  XRRCrtcInfo* output_crtc = XRRGetCrtcInfo(display, res, output_info->crtc);
	  XRRModeInfo* output_mode = res->modes + output;
	  
	  if (primary == res->outputs[output])
	    printf("* ");
	  
	  printf("%s:%i @ %i : %ix%i+%i+%i\n",
		 output_info->name, output, screen,
		 output_mode->width, output_mode->height,
		 output_crtc->x, output_crtc->y);
	}
    }
  
  return 0;
}

