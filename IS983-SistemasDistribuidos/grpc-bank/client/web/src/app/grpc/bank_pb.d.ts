import * as jspb from 'google-protobuf'

import * as google_protobuf_wrappers_pb from 'google-protobuf/google/protobuf/wrappers_pb'; // proto import: "google/protobuf/wrappers.proto"
import * as google_protobuf_empty_pb from 'google-protobuf/google/protobuf/empty_pb'; // proto import: "google/protobuf/empty.proto"


export class User extends jspb.Message {
  getId(): number;
  setId(value: number): User;

  getEmail(): string;
  setEmail(value: string): User;

  getName(): string;
  setName(value: string): User;

  getActive(): boolean;
  setActive(value: boolean): User;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): User.AsObject;
  static toObject(includeInstance: boolean, msg: User): User.AsObject;
  static serializeBinaryToWriter(message: User, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): User;
  static deserializeBinaryFromReader(message: User, reader: jspb.BinaryReader): User;
}

export namespace User {
  export type AsObject = {
    id: number;
    email: string;
    name: string;
    active: boolean;
  };
}

export class Account extends jspb.Message {
  getId(): number;
  setId(value: number): Account;

  getUserId(): number;
  setUserId(value: number): Account;

  getActive(): boolean;
  setActive(value: boolean): Account;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Account.AsObject;
  static toObject(includeInstance: boolean, msg: Account): Account.AsObject;
  static serializeBinaryToWriter(message: Account, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Account;
  static deserializeBinaryFromReader(message: Account, reader: jspb.BinaryReader): Account;
}

export namespace Account {
  export type AsObject = {
    id: number;
    userId: number;
    active: boolean;
  };
}

export class Transaction extends jspb.Message {
  getId(): number;
  setId(value: number): Transaction;

  getAccountId(): number;
  setAccountId(value: number): Transaction;

  getTransactionType(): TransactionType;
  setTransactionType(value: TransactionType): Transaction;

  getAmount(): number;
  setAmount(value: number): Transaction;

  getActive(): boolean;
  setActive(value: boolean): Transaction;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Transaction.AsObject;
  static toObject(includeInstance: boolean, msg: Transaction): Transaction.AsObject;
  static serializeBinaryToWriter(message: Transaction, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Transaction;
  static deserializeBinaryFromReader(message: Transaction, reader: jspb.BinaryReader): Transaction;
}

export namespace Transaction {
  export type AsObject = {
    id: number;
    accountId: number;
    transactionType: TransactionType;
    amount: number;
    active: boolean;
  };
}

export class GetUserRequest extends jspb.Message {
  getId(): number;
  setId(value: number): GetUserRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetUserRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetUserRequest): GetUserRequest.AsObject;
  static serializeBinaryToWriter(message: GetUserRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetUserRequest;
  static deserializeBinaryFromReader(message: GetUserRequest, reader: jspb.BinaryReader): GetUserRequest;
}

export namespace GetUserRequest {
  export type AsObject = {
    id: number;
  };
}

export class GetUserResponse extends jspb.Message {
  getUser(): User | undefined;
  setUser(value?: User): GetUserResponse;
  hasUser(): boolean;
  clearUser(): GetUserResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetUserResponse.AsObject;
  static toObject(includeInstance: boolean, msg: GetUserResponse): GetUserResponse.AsObject;
  static serializeBinaryToWriter(message: GetUserResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetUserResponse;
  static deserializeBinaryFromReader(message: GetUserResponse, reader: jspb.BinaryReader): GetUserResponse;
}

export namespace GetUserResponse {
  export type AsObject = {
    user?: User.AsObject;
  };
}

export class ListUsersRequest extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListUsersRequest.AsObject;
  static toObject(includeInstance: boolean, msg: ListUsersRequest): ListUsersRequest.AsObject;
  static serializeBinaryToWriter(message: ListUsersRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListUsersRequest;
  static deserializeBinaryFromReader(message: ListUsersRequest, reader: jspb.BinaryReader): ListUsersRequest;
}

export namespace ListUsersRequest {
  export type AsObject = {
  };
}

export class ListUsersResponse extends jspb.Message {
  getUsersList(): Array<User>;
  setUsersList(value: Array<User>): ListUsersResponse;
  clearUsersList(): ListUsersResponse;
  addUsers(value?: User, index?: number): User;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListUsersResponse.AsObject;
  static toObject(includeInstance: boolean, msg: ListUsersResponse): ListUsersResponse.AsObject;
  static serializeBinaryToWriter(message: ListUsersResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListUsersResponse;
  static deserializeBinaryFromReader(message: ListUsersResponse, reader: jspb.BinaryReader): ListUsersResponse;
}

export namespace ListUsersResponse {
  export type AsObject = {
    usersList: Array<User.AsObject>;
  };
}

export class CreateUserRequest extends jspb.Message {
  getEmail(): string;
  setEmail(value: string): CreateUserRequest;

  getPassword(): string;
  setPassword(value: string): CreateUserRequest;

  getName(): string;
  setName(value: string): CreateUserRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateUserRequest.AsObject;
  static toObject(includeInstance: boolean, msg: CreateUserRequest): CreateUserRequest.AsObject;
  static serializeBinaryToWriter(message: CreateUserRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateUserRequest;
  static deserializeBinaryFromReader(message: CreateUserRequest, reader: jspb.BinaryReader): CreateUserRequest;
}

export namespace CreateUserRequest {
  export type AsObject = {
    email: string;
    password: string;
    name: string;
  };
}

export class CreateUserResponse extends jspb.Message {
  getUser(): User | undefined;
  setUser(value?: User): CreateUserResponse;
  hasUser(): boolean;
  clearUser(): CreateUserResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateUserResponse.AsObject;
  static toObject(includeInstance: boolean, msg: CreateUserResponse): CreateUserResponse.AsObject;
  static serializeBinaryToWriter(message: CreateUserResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateUserResponse;
  static deserializeBinaryFromReader(message: CreateUserResponse, reader: jspb.BinaryReader): CreateUserResponse;
}

export namespace CreateUserResponse {
  export type AsObject = {
    user?: User.AsObject;
  };
}

export class LoginRequest extends jspb.Message {
  getEmail(): string;
  setEmail(value: string): LoginRequest;

  getPassword(): string;
  setPassword(value: string): LoginRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): LoginRequest.AsObject;
  static toObject(includeInstance: boolean, msg: LoginRequest): LoginRequest.AsObject;
  static serializeBinaryToWriter(message: LoginRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): LoginRequest;
  static deserializeBinaryFromReader(message: LoginRequest, reader: jspb.BinaryReader): LoginRequest;
}

export namespace LoginRequest {
  export type AsObject = {
    email: string;
    password: string;
  };
}

export class LoginResponse extends jspb.Message {
  getUser(): User | undefined;
  setUser(value?: User): LoginResponse;
  hasUser(): boolean;
  clearUser(): LoginResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): LoginResponse.AsObject;
  static toObject(includeInstance: boolean, msg: LoginResponse): LoginResponse.AsObject;
  static serializeBinaryToWriter(message: LoginResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): LoginResponse;
  static deserializeBinaryFromReader(message: LoginResponse, reader: jspb.BinaryReader): LoginResponse;
}

export namespace LoginResponse {
  export type AsObject = {
    user?: User.AsObject;
  };
}

export class GetAccountByUserRequest extends jspb.Message {
  getUserId(): number;
  setUserId(value: number): GetAccountByUserRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetAccountByUserRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetAccountByUserRequest): GetAccountByUserRequest.AsObject;
  static serializeBinaryToWriter(message: GetAccountByUserRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetAccountByUserRequest;
  static deserializeBinaryFromReader(message: GetAccountByUserRequest, reader: jspb.BinaryReader): GetAccountByUserRequest;
}

export namespace GetAccountByUserRequest {
  export type AsObject = {
    userId: number;
  };
}

export class GetAccountByUserResponse extends jspb.Message {
  getAccount(): Account | undefined;
  setAccount(value?: Account): GetAccountByUserResponse;
  hasAccount(): boolean;
  clearAccount(): GetAccountByUserResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetAccountByUserResponse.AsObject;
  static toObject(includeInstance: boolean, msg: GetAccountByUserResponse): GetAccountByUserResponse.AsObject;
  static serializeBinaryToWriter(message: GetAccountByUserResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetAccountByUserResponse;
  static deserializeBinaryFromReader(message: GetAccountByUserResponse, reader: jspb.BinaryReader): GetAccountByUserResponse;
}

export namespace GetAccountByUserResponse {
  export type AsObject = {
    account?: Account.AsObject;
  };
}

export class ListAccountsRequest extends jspb.Message {
  getUserId(): google_protobuf_wrappers_pb.UInt64Value | undefined;
  setUserId(value?: google_protobuf_wrappers_pb.UInt64Value): ListAccountsRequest;
  hasUserId(): boolean;
  clearUserId(): ListAccountsRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListAccountsRequest.AsObject;
  static toObject(includeInstance: boolean, msg: ListAccountsRequest): ListAccountsRequest.AsObject;
  static serializeBinaryToWriter(message: ListAccountsRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListAccountsRequest;
  static deserializeBinaryFromReader(message: ListAccountsRequest, reader: jspb.BinaryReader): ListAccountsRequest;
}

export namespace ListAccountsRequest {
  export type AsObject = {
    userId?: google_protobuf_wrappers_pb.UInt64Value.AsObject;
  };
}

export class ListAccountsResponse extends jspb.Message {
  getAccountsList(): Array<Account>;
  setAccountsList(value: Array<Account>): ListAccountsResponse;
  clearAccountsList(): ListAccountsResponse;
  addAccounts(value?: Account, index?: number): Account;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListAccountsResponse.AsObject;
  static toObject(includeInstance: boolean, msg: ListAccountsResponse): ListAccountsResponse.AsObject;
  static serializeBinaryToWriter(message: ListAccountsResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListAccountsResponse;
  static deserializeBinaryFromReader(message: ListAccountsResponse, reader: jspb.BinaryReader): ListAccountsResponse;
}

export namespace ListAccountsResponse {
  export type AsObject = {
    accountsList: Array<Account.AsObject>;
  };
}

export class CreateAccountRequest extends jspb.Message {
  getUserId(): number;
  setUserId(value: number): CreateAccountRequest;

  getActive(): google_protobuf_wrappers_pb.BoolValue | undefined;
  setActive(value?: google_protobuf_wrappers_pb.BoolValue): CreateAccountRequest;
  hasActive(): boolean;
  clearActive(): CreateAccountRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateAccountRequest.AsObject;
  static toObject(includeInstance: boolean, msg: CreateAccountRequest): CreateAccountRequest.AsObject;
  static serializeBinaryToWriter(message: CreateAccountRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateAccountRequest;
  static deserializeBinaryFromReader(message: CreateAccountRequest, reader: jspb.BinaryReader): CreateAccountRequest;
}

export namespace CreateAccountRequest {
  export type AsObject = {
    userId: number;
    active?: google_protobuf_wrappers_pb.BoolValue.AsObject;
  };
}

export class CreateAccountResponse extends jspb.Message {
  getAccount(): Account | undefined;
  setAccount(value?: Account): CreateAccountResponse;
  hasAccount(): boolean;
  clearAccount(): CreateAccountResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateAccountResponse.AsObject;
  static toObject(includeInstance: boolean, msg: CreateAccountResponse): CreateAccountResponse.AsObject;
  static serializeBinaryToWriter(message: CreateAccountResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateAccountResponse;
  static deserializeBinaryFromReader(message: CreateAccountResponse, reader: jspb.BinaryReader): CreateAccountResponse;
}

export namespace CreateAccountResponse {
  export type AsObject = {
    account?: Account.AsObject;
  };
}

export class ListTransactionsRequest extends jspb.Message {
  getAccountId(): google_protobuf_wrappers_pb.UInt64Value | undefined;
  setAccountId(value?: google_protobuf_wrappers_pb.UInt64Value): ListTransactionsRequest;
  hasAccountId(): boolean;
  clearAccountId(): ListTransactionsRequest;

  getUserId(): google_protobuf_wrappers_pb.UInt64Value | undefined;
  setUserId(value?: google_protobuf_wrappers_pb.UInt64Value): ListTransactionsRequest;
  hasUserId(): boolean;
  clearUserId(): ListTransactionsRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListTransactionsRequest.AsObject;
  static toObject(includeInstance: boolean, msg: ListTransactionsRequest): ListTransactionsRequest.AsObject;
  static serializeBinaryToWriter(message: ListTransactionsRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListTransactionsRequest;
  static deserializeBinaryFromReader(message: ListTransactionsRequest, reader: jspb.BinaryReader): ListTransactionsRequest;
}

export namespace ListTransactionsRequest {
  export type AsObject = {
    accountId?: google_protobuf_wrappers_pb.UInt64Value.AsObject;
    userId?: google_protobuf_wrappers_pb.UInt64Value.AsObject;
  };
}

export class ListTransactionsResponse extends jspb.Message {
  getTransactionsList(): Array<Transaction>;
  setTransactionsList(value: Array<Transaction>): ListTransactionsResponse;
  clearTransactionsList(): ListTransactionsResponse;
  addTransactions(value?: Transaction, index?: number): Transaction;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ListTransactionsResponse.AsObject;
  static toObject(includeInstance: boolean, msg: ListTransactionsResponse): ListTransactionsResponse.AsObject;
  static serializeBinaryToWriter(message: ListTransactionsResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ListTransactionsResponse;
  static deserializeBinaryFromReader(message: ListTransactionsResponse, reader: jspb.BinaryReader): ListTransactionsResponse;
}

export namespace ListTransactionsResponse {
  export type AsObject = {
    transactionsList: Array<Transaction.AsObject>;
  };
}

export class CreateTransactionRequest extends jspb.Message {
  getAccountId(): number;
  setAccountId(value: number): CreateTransactionRequest;

  getAmount(): number;
  setAmount(value: number): CreateTransactionRequest;

  getTransactionType(): TransactionType;
  setTransactionType(value: TransactionType): CreateTransactionRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateTransactionRequest.AsObject;
  static toObject(includeInstance: boolean, msg: CreateTransactionRequest): CreateTransactionRequest.AsObject;
  static serializeBinaryToWriter(message: CreateTransactionRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateTransactionRequest;
  static deserializeBinaryFromReader(message: CreateTransactionRequest, reader: jspb.BinaryReader): CreateTransactionRequest;
}

export namespace CreateTransactionRequest {
  export type AsObject = {
    accountId: number;
    amount: number;
    transactionType: TransactionType;
  };
}

export class CreateTransactionResponse extends jspb.Message {
  getTransaction(): Transaction | undefined;
  setTransaction(value?: Transaction): CreateTransactionResponse;
  hasTransaction(): boolean;
  clearTransaction(): CreateTransactionResponse;

  getAccount(): Account | undefined;
  setAccount(value?: Account): CreateTransactionResponse;
  hasAccount(): boolean;
  clearAccount(): CreateTransactionResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateTransactionResponse.AsObject;
  static toObject(includeInstance: boolean, msg: CreateTransactionResponse): CreateTransactionResponse.AsObject;
  static serializeBinaryToWriter(message: CreateTransactionResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateTransactionResponse;
  static deserializeBinaryFromReader(message: CreateTransactionResponse, reader: jspb.BinaryReader): CreateTransactionResponse;
}

export namespace CreateTransactionResponse {
  export type AsObject = {
    transaction?: Transaction.AsObject;
    account?: Account.AsObject;
  };
}

export class AccountRequest extends jspb.Message {
  getAccountId(): number;
  setAccountId(value: number): AccountRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): AccountRequest.AsObject;
  static toObject(includeInstance: boolean, msg: AccountRequest): AccountRequest.AsObject;
  static serializeBinaryToWriter(message: AccountRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): AccountRequest;
  static deserializeBinaryFromReader(message: AccountRequest, reader: jspb.BinaryReader): AccountRequest;
}

export namespace AccountRequest {
  export type AsObject = {
    accountId: number;
  };
}

export class BankTotal extends jspb.Message {
  getTotalAmount(): number;
  setTotalAmount(value: number): BankTotal;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BankTotal.AsObject;
  static toObject(includeInstance: boolean, msg: BankTotal): BankTotal.AsObject;
  static serializeBinaryToWriter(message: BankTotal, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): BankTotal;
  static deserializeBinaryFromReader(message: BankTotal, reader: jspb.BinaryReader): BankTotal;
}

export namespace BankTotal {
  export type AsObject = {
    totalAmount: number;
  };
}

export class BankTotalUpdate extends jspb.Message {
  getTotalAmount(): number;
  setTotalAmount(value: number): BankTotalUpdate;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BankTotalUpdate.AsObject;
  static toObject(includeInstance: boolean, msg: BankTotalUpdate): BankTotalUpdate.AsObject;
  static serializeBinaryToWriter(message: BankTotalUpdate, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): BankTotalUpdate;
  static deserializeBinaryFromReader(message: BankTotalUpdate, reader: jspb.BinaryReader): BankTotalUpdate;
}

export namespace BankTotalUpdate {
  export type AsObject = {
    totalAmount: number;
  };
}

export enum TransactionType {
  TRANSACTION_TYPE_UNSPECIFIED = 0,
  DEPOSIT = 1,
  WITHDRAW = 2,
}
