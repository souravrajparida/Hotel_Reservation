import { UserRegistration } from './user-reg';

describe('UserRegistration', () => {
  it('should create an instance', () => {
    expect(new UserRegistration(0,"","","","","","")).toBeTruthy();
  });
});
