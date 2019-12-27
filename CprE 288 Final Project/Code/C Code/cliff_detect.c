#include "open_interface.h"

/**
 * Detects boundaries and cliffs in the 'neighborhood'.
 *
 * Authors : Daniel Nikolic
 *         : Ahmad Alramahi
 *         : Rithwik Gokhale
 *         : Hayden Sellars
 */

int *front_right_sense;
int *front_left_sense;
int *right_sense;
int *left_sense;

void cliff_detect(int *front_left, int *front_right, int *left, int *right)
{
	oi_t *sensor = oi_alloc();
    oi_init(sensor);
	
    front_left_sense = front_left;
    front_right_sense = front_right;
    right_sense = right;
    left_sense = left;

    oi_update(sensor);

    *front_right_sense = sensor -> cliffFrontRightSignal;
    *front_left_sense = sensor -> cliffFrontLeftSignal;
    *right_sense = sensor -> cliffRightSignal;
    *left_sense = sensor -> cliffLeftSignal;
	
	oi_free(sensor);
}
