1. Ek service package
2. Uske bhiter ek interface containing methods for the sending Email

1. to one sender
2. to multiple sender
3. to one sender in the form of html
4. to one sender including file also

## Important points

1. JavaMailSender class ko autowire karenge
2. Ek simple message class banayene , it is very important for modifying the messages 
    getting the information.
3. setTo, setMessage, setText, setFrom
4. mailsender.send(simpleMessageObject)

111. Isko ek baar test kar ke bhi dekhiyega
    Logfactory ko bhi include kariyega
