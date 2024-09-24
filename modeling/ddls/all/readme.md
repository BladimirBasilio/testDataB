##
For script can be run on `cloudsql` we must delete these sequences

###TABLESPACE "LIVERPOOL_DATA"

from this 
```USING INDEX TABLESPACE "LIVERPOOL_DATA"```
to this
```USING INDEX```

###TABLESPACE "LIVERPOOL_INDEXES"

from this 
```	USING INDEX TABLESPACE "LIVERPOOL_INDEXES"```
to this
```USING INDEX```