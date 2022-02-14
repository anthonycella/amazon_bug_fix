# amazon_bug_fix

This is the first project given to me by someone else outside of school (my dad). Amazon used to have a bug when it came to financial files.
This specific instance is when Amazon would convert a user's financial information to a Quicken Loans File (just a text file) it would not 
input the date started and date end periods. This would cause errors when trying to upload to Quicken Loans and my parents would have to 
fix the issue by hand. My task was to create a program that would fix the bug automatically without having to open the file by hand and type out the fix.
So to fix this I created a program that would open the file, find the start and end dates, and set them to a working format (the start date was set to the first transaction date
and the end date was set to the current date). In addition, so that my mother would have an easy time working the program, I included a "file explorer" functionality so that
when the user started up the program all they would have to do is select the file in the explorer and the program would go in and fix it automatically.
