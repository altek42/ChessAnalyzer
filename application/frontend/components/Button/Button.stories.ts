import type { Meta, StoryObj } from "@storybook/react";

import Button from "@/components/Button";

const meta = {
  title: "Components/Button",
  component: Button,
  parameters: {
    layout: "centered",
  },
  tags: ["autodocs"],
  argTypes: {
    variant: { control: "select", options: ['text', 'contained', 'outlined'] },
    color: { control: "select", options: ['primary', 'secondary'] }
  },
} satisfies Meta<typeof Button>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    children: 'Button',
    variant: 'contained',
  },
};
