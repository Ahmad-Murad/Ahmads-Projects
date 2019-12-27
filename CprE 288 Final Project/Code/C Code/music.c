#include "open_interface.h"

/**
 * Operates all music for the cyBot.
 *
 * Authors : Daniel Nikolic
 *         : Ahmad Alramahi
 *         : Rithwik Gokhale
 *         : Hayden Sellars
 */

void music1() // PALLET TOWN
{
    unsigned char notes[] = {62, 60, 59, 57, 67, 64, 66, 64, 62, 59, 55, 55, 57, 59, 60, 30, 54, 55, 57, 59, 60, 59, 57, 62, 60, 59, 62, 67, 66, 66, 67, 64, 62, 62, 60, 59, 57, 55, 62, 60, 59, 57, 55};
    unsigned char duration[] = {16, 16, 16, 16, 16, 16, 16, 16, 48, 16, 16, 16, 16, 16, 64, 16, 16, 16, 16, 48, 8, 8, 64, 16, 16, 16, 16, 16, 16, 16, 16, 48, 16, 64, 16, 16, 16, 16, 16, 16, 16, 16, 64};

    oi_loadSong(0, 43, notes, duration);
    oi_play_song(0);
    memset(notes, 0, sizeof(notes));
    memset(duration, 0, sizeof(notes));
}

void music2() // PIRATES OF THE CARIBBEAN
{
    unsigned char notes[] = {57, 60, 62, 62, 62, 64, 65, 65, 65, 67, 64, 64, 62, 60, 60, 62, 30, 57, 60, 62, 62, 62, 64, 65, 65, 65, 67, 64, 64, 62, 60, 62, 30};
    unsigned char duration[] = {8, 8, 16, 16, 8, 8, 16, 16, 8, 8, 16, 16, 8, 8, 16, 8, 8, 8, 8, 16, 16, 8, 8, 16, 16, 8, 8, 16, 16, 8, 8, 24, 8};

    oi_loadSong(1, 33, notes, duration);
    oi_play_song(1);
    memset(notes, 0, sizeof(notes));
    memset(duration, 0, sizeof(notes));
}

