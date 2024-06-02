import { NextComponentType, NextPage, NextPageContext } from 'next'
import { AppProps } from 'next/app'

import { LayoutKeys } from '../Layout'
export type MyPage<P = unknown, IP = P> = NextPage<P, IP> & {
  Layout?: LayoutKeys
}
export type MyAppProps = AppProps & {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  Component: NextComponentType<NextPageContext, any, any> & {
    Layout: LayoutKeys
  }
}
