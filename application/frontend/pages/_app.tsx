import React from 'react'
import { ThemeProvider } from "@mui/material";
import { AppProps } from "next/app";
import theme from "styles/Theme";

function App({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider theme={theme}>
      <Component {...pageProps} />;
    </ThemeProvider>
  );
}

export default App;
