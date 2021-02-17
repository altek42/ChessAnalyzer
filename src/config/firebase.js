import firebase from "firebase/app";
import "firebase/auth";
import "firebase/database";

const config = {
  apiKey: "AIzaSyA-Je0BFqj_i6BriFk9_X0C_x-4s8Eta0A",
  authDomain: "chess-9fa38.firebaseapp.com",
  databaseURL: "https://chess-9fa38-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "chess-9fa38",
  storageBucket: "chess-9fa38.appspot.com",
  messagingSenderId: "802688849153",
  appId: "1:802688849153:web:f6ae599f934278f9027700"
};
firebase.initializeApp(config);
export default firebase;
