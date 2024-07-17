export interface Performance {
  id: number;
  employeeId: number;
  reviewDate: Date;
  reviewerId: number;
  score: number;
  comments?: string;
}
