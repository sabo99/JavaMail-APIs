# Java Mail APIs for Android *Kotlin* or *Java*

Java Mail Sender Android with Kotlin & Java

### **Feature App** :

- **API JavaMail** (_Gmail Sender Message_)
    <br><br>


## **Important!**
### Config Google Account
- Make sure not to use **private email**
- Go To <a href="https://myaccount.google.com/">`Google Account`</a>
  <br>
- Go To **`Security`**
- Turn off **`2-Step Verification`**
- Turn off **`Less secure app access`**

### Config JavaMail APIs

In the file <a href="app/src/main/java/com/sabo/todolist_ci4_restful/Helper/JavaMailAPI/Credentials.kt">`Credentials.kt`</a> change the following line with the email that will be used as Sender

```kotlin
object Credentials {
    const val EMAIL_SENDER = "your_email"
    const val PASSWORD_SENDER = "your_password"
}
```
