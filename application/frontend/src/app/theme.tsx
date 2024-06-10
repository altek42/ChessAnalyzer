'use client'
import { Roboto } from 'next/font/google'
import { createTheme } from '@mui/material'
import { grey } from '@mui/material/colors'

const roboto = Roboto({
  weight: ['300', '400', '500', '700'],
  subsets: ['latin'],
  display: 'swap',
})

const theme = createTheme({
  palette: {
    background: {
      default: grey[200],
    },
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          borderRadius: 30,
        },
      },
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          borderRadius: 10,
        },
      },
    },
  },
  typography: {
    fontFamily: roboto.style.fontFamily,
  },
})

export default theme
