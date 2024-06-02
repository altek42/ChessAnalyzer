import { Container } from '@mui/material'
import { red } from '@mui/material/colors'
import React, { PropsWithChildren } from 'react'

const AppLayout = ({ children }: PropsWithChildren) => {
  return (
    <Container
      sx={{
        backgroundColor: red[200],
      }}
      maxWidth='sm'
    >
      {children}
    </Container>
  )
}

export default AppLayout
