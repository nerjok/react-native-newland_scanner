using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace New.Land.Scanner.RNNewLandScanner
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNNewLandScannerModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNNewLandScannerModule"/>.
        /// </summary>
        internal RNNewLandScannerModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNNewLandScanner";
            }
        }
    }
}
