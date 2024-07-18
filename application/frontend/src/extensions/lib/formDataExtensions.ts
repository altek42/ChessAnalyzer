export function extendFormData() {
  if (typeof FormData.prototype.getString !== 'function') {
    FormData.prototype.getString = function (name: string): string {
      return this.get(name) as string
    }
  }
}
