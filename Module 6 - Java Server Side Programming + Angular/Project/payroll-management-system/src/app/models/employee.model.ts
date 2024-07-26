export interface Employee {
  id: number;
  name: string;
  email: string;
  phoneNumber?: string;
  departmentId: number;
  position: string;
  dateOfJoining: Date;
  salary: number;
}
