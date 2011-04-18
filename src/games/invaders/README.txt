=== Revision 2 ===

Hacked in some music, and added proper edge wrapping.


=== Revision 1 ===

For the GoldenT game assignment, I modified StarStreak Invasion, which is a 
variation on space invaders. The original code featured an impressive number 
of consecutively named variables that should have been arrays, and with some 
other issues, the code for the game was about 700 lines. I added a second level, 
changed the ship steering, and generally tried to not make the code run 
completely out of one file. I had some issues with the collision handlers on 
the second level, as the ships become invulnerable. Alternatively, it is also 
possible that the ships just keep getting created really fast, which would also 
explain the very poor performance on level 2.

To skip to the next level, use the "\" key.

Hint: The enemies don't fire, but the first level is tricky because of the 
enemy placement. You have to use the up and down arrow keys to set up a 
harmonic motion sufficient to send your hero ship below those of the enemies. 