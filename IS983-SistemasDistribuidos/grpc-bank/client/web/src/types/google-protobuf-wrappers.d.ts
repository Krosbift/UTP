declare module 'google-protobuf/google/protobuf/wrappers_pb' {
  export class UInt64Value {
    constructor();
    setValue(value: number | string): void;
    getValue(): number | string;
    serializeBinary(): Uint8Array;
    toObject(): any;
  }
  export class StringValue {
    constructor();
    setValue(value: string): void;
    getValue(): string;
    serializeBinary(): Uint8Array;
    toObject(): any;
  }
  export default {
    UInt64Value: UInt64Value,
    StringValue: StringValue,
  };
}
