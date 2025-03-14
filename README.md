Exhibition Rating App

The Exhibition Rating App is an Android application designed to facilitate seamless and efficient rating of exhibition groups using QR code scanning. This app leverages Firebase Realtime Database for dynamic data storage and Google Sign-In for user authentication, ensuring a secure and personalized experience.

Key Features

✅ QR Code Scanning: Effortlessly scan QR codes assigned to each exhibition group for quick access to the rating interface.✅ Dynamic Rating System: Users can rate groups using a predefined star rating system, stored directly in Firebase for real-time updates.✅ User Authentication: Integrated with Google Sign-In to ensure secure access and track ratings by user email.✅ Group-wise Rating Pages: Each group has its dedicated rating page to ensure clarity and ease of use.✅ Admin Dashboard: View consolidated ratings for all groups with organized data visualization.✅ ScrollView Display: All group ratings are neatly presented in a ScrollView for quick browsing.

Technologies Used

Java (Core Logic)

XML (UI Design)

Firebase Realtime Database (Data Storage)

Google Sign-In API (Authentication)

Gradle (Groovy DSL) for project build and dependency management

How it Works

Sign In with Google – Ensures only authenticated users can rate the exhibition groups.

Scan QR Code – The app fetches the group ID from the scanned code and navigates to the respective rating page.

Submit Rating – Users select the desired star rating, which is stored in Firebase with their email as an identifier.

View All Ratings – A dedicated page displays the ratings of all groups in a structured ScrollView.

Future Enhancements

🔹 Enhanced UI design with improved aesthetics and intuitive navigation.🔹 Detailed analytics for admin insights into group performance.🔹 Offline rating storage with auto-sync when the device reconnects to the internet.

Setup Instructions

Clone this repository.

Open the project in Android Studio.

Add your Firebase Realtime Database credentials and configure Google Sign-In in your project.

Build and run the app on an Android device or emulator.

Contribution

Contributions are welcome! Feel free to submit pull requests or open issues for suggestions and improvements.

License

This project is licensed under the MIT License.

💬 If you have any questions or feedback, feel free to reach out!
