export interface Blog {
  id: number,
  title: string,
  blogText: string,
  author: Author,
  attraction: Attraction
}

export interface Author{
  id: number,
  firstname: string,
  lastname: string
}

export interface Attraction{
  id: number,
  name: string,
  description: string
}
