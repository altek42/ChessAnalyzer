import { Layout } from '@/components/Layout'
import { MyAppProps } from '@/components/common/types'
import { CssBaseline, ThemeProvider } from '@mui/material'
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter'
import React from 'react'
import theme from 'styles/Theme'

const App = ({ Component, pageProps }: MyAppProps) => {
  const LayoutComponent = Layout[Component.Layout] ?? Layout['default']
  return (
    <AppRouterCacheProvider>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <LayoutComponent>
          <Component {...pageProps} />
        </LayoutComponent>
      </ThemeProvider>
    </AppRouterCacheProvider>
  )
}

export default App
