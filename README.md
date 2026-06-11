# Course Project Plan (Version 1)

so I got things set up with the basic skeleton for our 2000s pop songs project. let's treat this readme as our tracker, so when you finish a part just update this file with your changes so we all know where things stand.

i went ahead and started working on the SearchEngine part since i want to get that wrapped up by the end of this weekend. i think i've done about 60-70% of that part at this point.

also about the songs.txt file - so basically instead of dealing with YAML or anything complicated we're just using a plain text file. it's formatted really simple with labeled lines like "title: Toxic" and "artist: Britney" separated by blank lines. 
... 
but we still have a bunch of stuff to fill in to get this working. there's about maybe 70-60% of the app logic left to do across a few files. I put "TODO" comments in the code where we need to fill things out. I was thinking we could divide it up like this:

teammate 1:
can you handle the Song.java and WordID.java files? mostly just need to implement equals and hashCode. 

teammate 2:
could you do the TitleComparator? it's literally a method to compare song titles alphabetically.

teammate 3:
can you take a look at SongLibrary.java? I did most of it but updateText, changeTitle, and saveToFile still need to be written. this one is a bit more challenging so take your time with it.

teammate 4:
CustomerInterface.java needs some work. the menu loop is there but deleteRecord, updateRecord, and showStatistics are empty. this is mostly just printing and reading input so it should be pretty straightforward.

Jake - me:
I'm doing the SearchEngine (about 6-70% done) and I'll grab whatever is leftover if anyone gets stuck.

we really need to get all this code done by next thursday so we have time to do the walkthroughs and record our presentation video. let me know if anyone has issues or wants to swap parts. let's get it done pronto!

once everyone finishes their pieces, we need to do a full test run. let's plan to have all the code pushed by the end of the weekend, or tuesday at the absolute latest. we need to compile it, run through the whole menu top to bottom, and make sure there are no bugs when we put it all together. it sounds like a big deal guys, but it's not that big once you understand it. it's only worth 5% of our mark but we still need to do well on this.

for the presentation, the rubric says it needs to be between 10 and 20 minutes total. we also have to make UML diagrams for our components (which is just a flowchart showing how the classes connect, we can just build them in google slides or canva). when discussing the diagrams, the person who wrote that part of the code has to speak about it. we'll delegate the presentation stuff on thursday in class. if we're not done recording by the end of class, let's meet at the library after class on thursday to finish recording everything. i'll stitch all the video clips together so we can plan to submit by thursday night.

Jake
