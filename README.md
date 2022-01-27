# AlertShortcut
The program starts an action when it receives a specific request over the network.
It was invented to alert admins from the user in critical situations...
In this implementation, I used:
server: waits for a connection and validates the token. If the token is valid, it starts playing the sound file and sends a message to the Telegram Bot.
client: for ease of use, I used a simple http shortcut (http://server:port?token=4321)
