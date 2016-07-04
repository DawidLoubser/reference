BEGIN { print "size -> file (date modified)" }
BEGIN { print "============================"}
/xml/ { mnth = substr($6,6,2);
  if ( mnth == "01" ) { month = "Jan" };
  if ( mnth == "02" ) { month = "Feb" };
  if ( mnth == "03" ) { month = "Mar" };
  if ( mnth == "04" ) { month = "Apr" };
  if ( mnth == "05" ) { month = "May" };
  if ( mnth == "06" ) { month = "Jun" };
  if ( mnth == "07" ) { month = "Jul" };
  if ( mnth == "08" ) { month = "Aug" };
  if ( mnth == "09" ) { month = "Sep" };
  if ( mnth == "10" ) { month = "Oct" };
  if ( mnth == "11" ) { month = "Nov" };
  if ( mnth == "12" ) { month = "Dec" };
  if ( substr($6,9,1) == "0" ) { day = substr($6,10,1) } else { day = substr($6,9,2) };
  print $5, $8, "("$7, "on", month, day ")"; }

# Example output:
# ls -l .. | awk -f ex3.awk
# size -> file (date modified)
# ============================
# 600 awk.xml (13:45 on Sep 6)
# 1181 coreFeatures.xml (05:30 on Sep 6)
# 1059 exercises.xml (11:41 on Sep 10)
# 386 introParagraph.xml (22:01 on Sep 5)
# 5346 patternMatching.xml (13:45 on Sep 6)
# 365 print.xml (13:45 on Sep 6)
# 441 solutions.xml (11:41 on Sep 10)
# 2699 typicalApplications.xml (05:32 on Sep 6)
# 3420 usingAwk.xml (13:45 on Sep 6)
# 471 whatIsAwk.xml (05:30 on Sep 6)
