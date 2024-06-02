import AppLayout from './AppLayout'
import SignLayout from './SignLayout'

export const Layout = {
  app: AppLayout,
  sign: SignLayout,
  default: AppLayout,
}

export type LayoutKeys = keyof typeof Layout
